package com.mygdx.game.controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;

public class KeyboardController implements InputProcessor {
    public boolean left,right,up,down;
    public boolean isMouse1Down,isMouse2Down,isMouse3Down;
    public boolean isDragged;
    public Vector2 mouseLocation = new Vector2();

    @Override
    public boolean keyDown(int keycode) {
        boolean keyProcess = false;
        switch(keycode){
            case Keys.LEFT:
                left=true;
                keyProcess=true;
                break;
            case Keys.RIGHT:
                right=true;
                keyProcess=true;
                break;
            case Keys.UP:
                up=true;
                keyProcess=true;
                break;
            case Keys.DOWN:
                down=true;
                keyProcess=true;
                break;
        }
        return keyProcess;
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean keyProcess = false;
        switch(keycode){
            case Keys.LEFT:
                left=false;
                keyProcess=true;
                break;
            case Keys.RIGHT:
                right=false;
                keyProcess=true;
                break;
            case Keys.UP:
                up=false;
                keyProcess=true;
                break;
            case Keys.DOWN:
                down=false;
                keyProcess=true;
                break;
        }
        return keyProcess;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == 0){
            isMouse1Down= true;
        }else if(button == 1){
            isMouse2Down = true;
        }else if(button == 2){
            isMouse3Down = true;
        }
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(button == 0){
            isMouse1Down= false;
        }else if(button == 1){
            isMouse2Down = false;
        }else if(button == 2){
            isMouse3Down = false;
        }
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        isDragged = true;
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouseLocation.x = screenX;
        mouseLocation.y= screenY;
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}
