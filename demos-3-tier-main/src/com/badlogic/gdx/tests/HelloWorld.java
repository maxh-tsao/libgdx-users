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
package com.badlogic.gdx.tests;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gdxuser.util.DemoWrapper;

public class HelloWorld extends DemoWrapper implements ApplicationListener {
	GL10 gl;

	SpriteBatch spriteBatch;
	Texture texture;
	BitmapFont font;
	Vector2 textPosition = new Vector2(100, 100);
	Vector2 textDirection = new Vector2(1, 1);

	@Override public void create () {
		gl = Gdx.gl10;
		gl.glDisable(GL10.GL_DEPTH_TEST);
//		gl.glEnable(GL10.GL_DEPTH_TEST);
//		gl.glDisable(GL10.GL_TEXTURE_2D);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glClearColor(0,0,0,1);
		
		font = new BitmapFont();
		font.setColor(Color.RED);
		texture = new Texture(Gdx.files.internal("data/badlogic.jpg"));
		spriteBatch = new SpriteBatch();
	}

	@Override public void render () {
		int centerX = Gdx.graphics.getWidth() / 2;
		int centerY = Gdx.graphics.getHeight() / 2;

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		// more fun but confusing :)
		//textPosition.add(textDirection.tmp().mul(Gdx.graphics.getDeltaTime()).mul(60));
		textPosition.x += textDirection.x * Gdx.graphics.getDeltaTime() * 60;
		textPosition.y += textDirection.y * Gdx.graphics.getDeltaTime() * 60;

		if (textPosition.x < 0 ) {
			textDirection.x = -textDirection.x;
			textPosition.x = 0;
		}
		if(textPosition.x > Gdx.graphics.getWidth()) {
			textDirection.x = -textDirection.x;
			textPosition.x = Gdx.graphics.getWidth();
		}
		if (textPosition.y < 0) {
			textDirection.y = -textDirection.y;
			textPosition.y = 0;			
		}
		if (textPosition.y > Gdx.graphics.getHeight()) {
			textDirection.y = -textDirection.y;
			textPosition.y = Gdx.graphics.getHeight();			
		}

		spriteBatch.begin();
//		spriteBatch.setColor(Color.WHITE);
		spriteBatch.draw(texture, 
							  centerX - texture.getWidth() / 2, 
							  centerY - texture.getHeight() / 2, 
							  0, 0, texture.getWidth(), texture.getHeight());		
		font.draw(spriteBatch, "Hello World!", (int)textPosition.x, (int)textPosition.y);
		spriteBatch.end();
	}

	@Override public void resize (int width, int height) {
		spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		textPosition.set(0, 0);
	}

	@Override public void pause () {

	}

	@Override public void resume () {

	}
	
	@Override public void dispose () {

	}
	
}
