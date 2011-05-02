package com.testapp;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameOverScreen implements Screen {
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	float screenWidth = Gdx.graphics.getWidth();
	float screenHeight = Gdx.graphics.getHeight();
	float time = 0f;
	private Texture texture = new Texture("data/titleBackground.png");
	private TextureRegion background = new TextureRegion(texture,0,0,320,480);
	private boolean isDone = false;
	private Music music2 = Gdx.audio.newMusic(Gdx.files.internal("data/wintheme.mp3"));
	
	public GameOverScreen(Application app){
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		font.setScale(1.5f);
		font.setColor(1f, 91/255f, 0f, 1);
		music2.play();
	}

	@Override
	public void update(Application app) {
		if(time <= 400){
			time += Gdx.graphics.getDeltaTime() * 100;
		}
		if(time >= 200){
		isDone = app.getInput().isTouched();
		}
		
	}

	@Override
	public void render(Application app) {
		Gdx.gl.glClear(Color.BLACK.toIntBits());		
		spriteBatch.begin();
		spriteBatch.setColor(Color.WHITE);
		spriteBatch.draw(background, 0f, 0f,screenWidth,screenHeight);
		font.draw(spriteBatch,"Congratulations!",Gdx.graphics.getWidth()/2 - 140,Gdx.graphics.getHeight()/2);
		font.draw(spriteBatch,"You have beat the game", Gdx.graphics.getWidth()/2 - 120, Gdx.graphics.getHeight()/2 - 20);
		font.draw(spriteBatch, Settings.wins + " times so far.", Gdx.graphics.getWidth()/2 - 90, Gdx.graphics.getHeight()/2 -40);
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
		music2.dispose();
		
	}

}
