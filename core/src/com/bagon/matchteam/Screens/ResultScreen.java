package com.bagon.matchteam.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.bagon.matchteam.Util.PeopleCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by VM Win10 on 5/5/2559.
 */
public class ResultScreen implements Screen {
    private Game game;
    private Stage stage;
    public OrthographicCamera cam;

    private Label lblPeople, lblTeam;
    private TextButton btnBack;

    private int people, team;
    private ArrayList<String> lstTeam;
    private ArrayList<PeopleCard> lstPC;

    public ResultScreen (Game g, int p_People, int p_Team){
        game = g;
        people = p_People;
        team = p_Team;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 480, 800);

        createTeamList();

        stage = new Stage();
//        stage.getViewport().setCamera(cam);
        Gdx.input.setInputProcessor(stage);

        //=== Initial Data ===
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        float screenX = Gdx.graphics.getWidth();
        float screenY = Gdx.graphics.getHeight();

        lblPeople = new Label(Integer.toString(people), skin);
        lblPeople.setPosition((screenX/2) - 150, (screenY - 60));

        lblTeam = new Label(Integer.toString(team), skin);
        lblTeam.setPosition((screenX/2) - 150, (screenY - 120));

        btnBack = new TextButton("Back", skin);
        btnBack.setSize(160, 120);
        btnBack.setPosition(0,(screenY - 120));
        btnBack.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent e, float x , float y, int point, int button){
                backClick();
            }
        });

        stage.addActor(lblPeople);
        stage.addActor(lblTeam);
        stage.addActor(btnBack);

        addLuckyDraw();
    }

    private void createTeamList() {
        lstPC = new ArrayList<PeopleCard>();
        lstTeam = new ArrayList<String>();
        lstTeam.add(0, "A");
        lstTeam.add(1, "B");
        lstTeam.add(2, "C");
        lstTeam.add(3, "D");
        lstTeam.add(4, "E");
        lstTeam.add(5, "F");
        lstTeam.add(6, "G");
        lstTeam.add(7, "H");

        for (int i = 0; i < people; i++) {
            PeopleCard pc = new PeopleCard();

            pc.setNumber(i);
            pc.setTeam(lstTeam.get(i % team));

            lstPC.add(pc);
        }

        Collections.shuffle(lstPC);
    }

    private void addLuckyDraw() {

        Table table = new Table();
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        skin.getFont("default-font").getData().setScale(2f);

        final ArrayList<TextButton> lstButton = new ArrayList<TextButton>();

        float screenX = Gdx.graphics.getWidth();
        float screenY = Gdx.graphics.getHeight();

        for (int i = 0; i < people; i++) {
            PeopleCard pc = lstPC.get(i);

            if( i % 4 == 0)
            {
                table.row().height(250);
            }

            final TextButton tb = new TextButton(Integer.toString(i + 1), skin);
            tb.setUserObject(pc);
            lstButton.add(tb);

            tb.addListener(new ChangeListener(){
                @Override
//                public void touchUp(InputEvent e, float x , float y, int point, int button){
                    public void changed (ChangeListener.ChangeEvent event, Actor actor) {

                    TextButton tb1 = tb;
                    PeopleCard pc = (PeopleCard) tb1.getUserObject();

                    tb1.setText(pc.getTeam());
                    tb1.setColor(Color.RED);

                }
            });

            table.add(tb).expandX().fill().space(25);
        }

        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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

    private void backClick()
    {
        game.setScreen(new InputScreen(game));
    }
}
