package com.testapp;

import javax.media.opengl.GL;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GameScreen implements Screen{
	Input input;
	Vector3 touchpoint = new Vector3();
	Vector2 enemyPos = new Vector2(100,100);
	Vector2 enemyDir = new Vector2(1,1);
	//OrthographicCamera guiCam;
	SpriteBatch spriteBatch = new SpriteBatch();
    BitmapFont font = new BitmapFont();
    BitmapFont winText = new BitmapFont();
    BitmapFont scoreText = new BitmapFont();
    Vector2 textPosition = new Vector2(100, 100);
    Vector2 textDirection = new Vector2(1, 1);
    Texture texture = new Texture(Gdx.files.internal("data/selection.gif"));
    Texture enemy = new Texture(Gdx.files.internal("data/enemy.gif"));
    Rectangle winBounds = new Rectangle(Gdx.graphics.getWidth()/2 -25,
			Gdx.graphics.getHeight()/2 -25,
			50,
			50 );
    Texture background = new Texture(Gdx.files.internal("data/gameBackground.png"));
    TextureRegion backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
    Sound win = Gdx.audio.newSound(Gdx.files.internal("data/win.mp3"));
    boolean justWon = false;
	static final float FRUSTUM_WIDTH = 10;
	static final float FRUSTUM_HEIGHT = 15;
	float screenWidth = Gdx.graphics.getWidth();
	float screenHeight = Gdx.graphics.getHeight();
	int wins = 0;
	int score = 0;
	private boolean isDone = false;


	@Override
	public void update(Application app) {
		// TODO Auto-generated method stub

        font.setColor(Color.RED);
        winText.setColor(Color.BLACK);
        scoreText.setColor(Color.BLUE);
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
		if(score > 5000){
			isDone = true;
		}

	}

	@Override
	public void render(Application app) {
		Gdx.gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		
		if(Gdx.input.isTouched()){
			touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			}

		//guiCam.update();
		int centerX = Gdx.graphics.getWidth() / 2;
        int centerY = Gdx.graphics.getHeight() / 2;
        spriteBatch.begin();
        spriteBatch.setColor(Color.WHITE);

        //spriteBatch.disableBlending();
        spriteBatch.draw(backgroundRegion, 0f, 0f, screenWidth, screenHeight);
        spriteBatch.draw(texture, 
                touchpoint.x - texture.getWidth()/2, 
                -touchpoint.y + Gdx.graphics.getHeight() - texture.getHeight()/2, 
                0, 0, texture.getWidth(), texture.getHeight());
        font.draw(spriteBatch, touchpoint.x + " " + -touchpoint.y + " " + Gdx.graphics.getFramesPerSecond() + " " + Gdx.graphics.getDeltaTime(), 20, 20);
        winText.draw(spriteBatch, "Touch here to win", centerX - 50, centerY+50);
        scoreText.draw(spriteBatch,"Score: " + score,screenWidth-120, screenHeight-20);
        
        //Moving the enemy around
        enemyPos.x += enemyDir.x * Gdx.graphics.getDeltaTime() * 60;
		enemyPos.y += enemyDir.y * Gdx.graphics.getDeltaTime() * 60;

		if (enemyPos.x < 0) {
			enemyDir.x = -enemyDir.x;
			enemyPos.x = 0;
		}
		if(enemyPos.x > screenWidth - enemy.getWidth()) {
			enemyDir.x = -enemyDir.x;
			enemyPos.x = screenWidth - enemy.getWidth();
		}
		if (enemyPos.y < 0) {
			enemyDir.y = -enemyDir.y;
			enemyPos.y = 0;			
		}
		if (enemyPos.y > screenHeight - enemy.getHeight()) {
			enemyDir.y = -enemyDir.y;
			enemyPos.y = screenHeight - enemy.getHeight();			
		}
		spriteBatch.draw(enemy,(int)enemyPos.x, (int)enemyPos.y, 0, 0, enemy.getWidth(), enemy.getHeight());
       
		if (OverlapTester.pointInRectangle(winBounds, touchpoint.x, touchpoint.y)){
        	if(justWon == false){
            	win.play();
            	wins++;
            	score += 1000;
            	justWon = true;
        	}

        	
        }else {
            justWon = false;
        }
        spriteBatch.end();
       
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return isDone;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		spriteBatch.dispose();
		win.dispose();
		MainMenu.music.stop();
	}

}
