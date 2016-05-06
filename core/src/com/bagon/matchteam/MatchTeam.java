package com.bagon.matchteam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bagon.matchteam.Screens.InputScreen;

public class MatchTeam extends Game {


	public void create() {
		this.setScreen(new InputScreen(this));
	}

	public void render() {
		super.render(); //important!
	}

	public void dispose() {
//		batch.dispose();
//		font.dispose();
	}
}
