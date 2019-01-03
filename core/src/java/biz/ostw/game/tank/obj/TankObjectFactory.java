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
import biz.ostw.libgdx.DrawUtils;

public class TankObjectFactory extends ObjectFactory {

    protected final static float HALF_SIZE = 250;

    private final BodyDef bodyDef;

    private final Shape shape;

    {
        this.bodyDef = new BodyDef();
        this.bodyDef.type = BodyDef.BodyType.DynamicBody;

        this.shape = new PolygonShape();
        ((PolygonShape) shape).setAsBox(HALF_SIZE * DrawUtils.MPP, HALF_SIZE * DrawUtils.MPP);
    }

    @Override
    public Object[] supportedKeys() {
        return TankType.values();
    }

    @Override
    public Tank build(World world, Object type, Object... params) {

        Body body = this.createBody(world);
        TextureRegion bodyTexture = TextureFactory.getRegion("tank/0/body");
        Array<? extends TextureRegion> trackTextures = TextureFactory.getRegions("tank/0/track");
        Animation<TextureRegion> textureRegionAnimation = new Animation<TextureRegion>(0.1f, trackTextures, Animation.PlayMode.LOOP);


        return new Tank(body, bodyTexture, textureRegionAnimation, (TankType) type);
    }

    private Body createBody(World world) {
        Body body = world.createBody(this.bodyDef);
        body.setLinearDamping(0);
        body.setActive(true);
        body.setAwake(true);
        Fixture fixture = body.createFixture(this.shape, 1f);

        return body;
    }

    @Override
    public void dispose() {
        this.shape.dispose();
    }
}
