
/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.testapp;

import javax.media.opengl.GL;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;


/**
 * @author Bradley Mullis
 * */
public class testappexample implements ApplicationListener {
	Input input;
	Vector3 touchpoint;
	OrthographicCamera guiCam;
	SpriteBatch spriteBatch;
    BitmapFont font;
    Vector2 textPosition = new Vector2(100, 100);
    Vector2 textDirection = new Vector2(1, 1);
    Texture texture;
    Rectangle winBounds;

	@Override
	public void create() {
		// TODO Auto-generated method stub
		guiCam = new OrthographicCamera(320, 480);
		touchpoint = new Vector3();
		font = new BitmapFont();
        font.setColor(Color.RED);
        spriteBatch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("data/face.gif"));
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
		winBounds = new Rectangle(Gdx.graphics.getWidth()/2 -25,
				Gdx.graphics.getHeight()/2 -25,
				50,
				50 );
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {

		Gdx.gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		
		if(Gdx.input.isTouched()){
			touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			}

		guiCam.update();
		int centerX = Gdx.graphics.getWidth() / 2;
        int centerY = Gdx.graphics.getHeight() / 2;
        spriteBatch.begin();
        //spriteBatch.setColor(Color.WHITE);
        spriteBatch.draw(texture, 
                touchpoint.x, 
                -(touchpoint.y) + Gdx.graphics.getHeight() , 
                0, 0, texture.getWidth(), texture.getHeight());
        font.draw(spriteBatch, touchpoint.x + " " + -touchpoint.y, 20, 20);
        spriteBatch.end();
        if (OverlapTester.pointInRectangle(winBounds, touchpoint.x, touchpoint.y)){
        	Gdx.gl.glClearColor(0, 1, 0, 1);
        }else Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);

	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}