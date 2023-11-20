package com.mygdx.bullettest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.bullettest.screens.Box2DScreen;
import com.mygdx.bullettest.screens.BulletScreen;
import com.mygdx.bullettest.screens.MainMenuScreen;

public class Lab8 extends Game {
	static public Skin skin;
	public Texture backTexture;
	public Image background;
	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("skins/glassy-ui.json"));

		backTexture = new Texture(Gdx.files.internal("images/background.png"));
		background = new Image(backTexture);
		background.setPosition(0,0);
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		this.setScreen(new MainMenuScreen(this));
	}

	@Override public void render () {
		super.render();
	}
	@Override public void dispose () {
		skin.dispose();
	}
}
