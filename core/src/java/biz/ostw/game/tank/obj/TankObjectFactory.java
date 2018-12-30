package biz.ostw.game.tank.obj;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import biz.ostw.game.tank.TextureFactory;

public class TankObjectFactory extends ObjectFactory {

    @Override
    public Object[] supportedKeys() {
        return TankType.values();
    }

    @Override
    public Tank build(World world, Object type) {

        BodyDef def = new BodyDef();

        def.type = BodyDef.BodyType.KinematicBody;

        Body body = world.createBody(def);

        TextureRegion bodyTexture = TextureFactory.getRegion("tank/0/body");
        Array<? extends TextureRegion> trackTextures = TextureFactory.getRegions("tank/0/track");
        Animation<TextureRegion> textureRegionAnimation = new Animation<TextureRegion>(0.1f, trackTextures, Animation.PlayMode.LOOP);


        return new Tank(body, bodyTexture, textureRegionAnimation);
    }
}
