package com.testapp;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class desktopteststarter {
	public static void main(String[] args){
		new JoglApplication(new testapp2(),
				"Super Awesome Winning Game!",
				480,320, false);
		
	}
}
