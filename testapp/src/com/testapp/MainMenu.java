package com.testapp;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainMenu implements Screen {
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	float screenWidth = Gdx.graphics.getWidth();
	float screenHeight = Gdx.graphics.getHeight();
	private Texture texture = new Texture("data/titleBackground.png");
	private TextureRegion background = new TextureRegion(texture,0,0,320,480);
	private boolean isDone = false;
	private float time = 0;
	public static Music music = Gdx.audio.newMusic(Gdx.files.internal("data/maintheme.mp3"));
	
	public MainMenu(Application app){
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		font.setScale(2.2f);
		music.setLooping(true);
		music.play();
		Settings.load();
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
		String text = "Touch Screen to start!";
		spriteBatch.draw(background, 0f, 0f,screenWidth,screenHeight);
		font.draw(spriteBatch,text,Gdx.graphics.getWidth()/2 - 160,Gdx.graphics.getHeight()/2);
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
		
	}

}
