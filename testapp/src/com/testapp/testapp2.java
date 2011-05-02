package com.testapp;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class testapp2 implements ApplicationListener {
	/** flag indicating whether we were initialized already **/
	private boolean isInitialized = false;

	/** the current screen **/
	private Screen screen;

	@Override
	public void create() {
		// TODO Auto-generated method stub
		if(!isInitialized){
			screen = new MainMenu(Gdx.app);
			isInitialized = true;
		}
	}
		

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		MainMenu.music.dispose();
		GameScreen.effect1.dispose();
		GameScreen.effect2.dispose();
		GameScreen.effect3.dispose();
		GameScreen.effect4.dispose();
		GameScreen.effect5.dispose();
		GameScreen.effect6.dispose();
		GameScreen.effect7.dispose();
		GameScreen.effect8.dispose();
		GameScreen.effect9.dispose();
		GameScreen.effect10.dispose();
		GameScreen.effect11.dispose();
		GameScreen.effect12.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		MainMenu.music.pause();
		Settings.save();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Application app = Gdx.app;
		screen.update(app);
		screen.render(app);
		if(screen.isDone()){
			screen.dispose();
			if(screen instanceof MainMenu){
				screen = new GameScreen();
			}
			else if(screen instanceof GameScreen){
				screen = new GameOverScreen(app);
			}
			else{
				screen = new MainMenu(app);
			}
		}
	}
		

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		MainMenu.music.play();
	}

}
