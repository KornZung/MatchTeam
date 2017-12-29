package com.bagon.matchteam.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by VM Win10 on 5/5/2559.
 */
public class InputScreen implements Screen {
    private Game game;
    private Stage stage;
    private SpriteBatch batch;
    private Texture img;
    private TextField txtNumPeople;
    private TextField txtNumTeam;
    private Label lblPeople;
    private Label lblTeam;
    private final TextButton btnSubmit;

    public InputScreen (Game g)
    {
        game = g;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //=== Initial Data ===
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        skin.getFont("default-font").getData().setScale(2f);
//        Skin skin = new Skin();
        float screenX = Gdx.graphics.getWidth();
        float screenY = Gdx.graphics.getHeight();

        txtNumPeople = new TextField("", skin);
        txtNumPeople.setSize(600, 120);
        txtNumPeople.setPosition((screenX/2) - 150,(screenY/2) + 70);
        txtNumPeople.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

        lblPeople = new Label("People", skin);
        lblPeople.setPosition(txtNumPeople.getX() - 230, (screenY/2) + 70);

        txtNumTeam = new TextField("", skin);
        txtNumTeam.setSize(600, 120);
        txtNumTeam.setPosition((screenX/2) - 150,(screenY/2) - 70);
        txtNumTeam.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

        lblTeam = new Label("Team", skin);
        lblTeam.setPosition(txtNumTeam.getX() - 230, (screenY/2) - 70);

        btnSubmit = new TextButton("Generate!", skin);
        btnSubmit.setSize(600, 120);
        btnSubmit.setPosition((screenX/2) - 150,(screenY/2) - 250);
        btnSubmit.addListener(new ChangeListener(){
           @Override
//            public void touchUp(InputEvent e, float x , float y, int point, int button){
           public void changed (ChangeEvent event, Actor actor) {
               submitClick();
           }
        });

        stage.addActor(txtNumPeople);
        stage.addActor(txtNumTeam);
        stage.addActor(btnSubmit);
        stage.addActor(lblPeople);
        stage.addActor(lblTeam);
    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

//        batch.begin();
//        batch.draw(img, 0, 0);
//        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void submitClick()
    {
//        txtNumTeam.setText(Integer.toString(Integer.parseInt(txtNumPeople.getText()) * 2));
        int people = Integer.parseInt(txtNumPeople.getText());
        int team = Integer.parseInt(txtNumTeam.getText());

        game.setScreen(new ResultScreen(game, people, team));
    }
}
