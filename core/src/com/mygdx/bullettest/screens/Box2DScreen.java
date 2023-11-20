package com.mygdx.bullettest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bullettest.Lab8;

public class Box2DScreen implements Screen {
    Lab8 game;
    World world;
    Box2DDebugRenderer debugRenderer;
    Body ball;
    Body ground;
    Body box;
    private float accumulator = 0;
    OrthographicCamera camera;
    private void CreateDynamicBody(){
        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,6);


        // add it to the world
        ball = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        CircleShape shape = new CircleShape();
        shape.setRadius(2f);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.75f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        ball.createFixture(fixtureDef);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }
    private void CreateStaticBody(){

        // create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -6);

        // add it to the world
        ground = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 1);

        // create the physical object in our body)
        // without this our body would just be data in the world
        ground.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }
    private void CreateKinematicBody(){
        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,-8);


        // add it to the world
        box = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        box.createFixture(fixtureDef);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();

        box.setLinearVelocity(0, 0.75f);
    }
    public Box2DScreen(Lab8 context){
        this.game = context;
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(32,24);

        CreateDynamicBody();
        CreateStaticBody();
        CreateKinematicBody();
    }
    public void logicStep(float delta){
        world.step(delta , 3, 3);
    }
    @Override
    public void render(float delta) {
        logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(world, camera.combined);

        if(Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)){
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
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
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
