package biz.ostw.game.tank.obj.tank;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import biz.ostw.game.tank.obj.CollisionConst;
import biz.ostw.game.tank.obj.factory.ObjectFactory;
import biz.ostw.libgdx.DrawUtils;
import biz.ostw.libgdx.resources.TextureFactory;

public class TankObjectFactory extends ObjectFactory {

    protected final static float HALF_SIZE = 250;

    private static final Filter FILTER = new Filter() {
        {
            this.categoryBits = CollisionConst.CATEGORY_TANK;
            this.maskBits = CollisionConst.CATEGORY_TANK | CollisionConst.CATEGORY_LANDSCAPE_STRICT;
        }
    };

    private final BodyDef bodyDef;

    private final Shape shape;

    {
        this.bodyDef = new BodyDef();
        this.bodyDef.type = BodyDef.BodyType.DynamicBody;

        this.shape = new PolygonShape();
        ((PolygonShape) shape).setAsBox(HALF_SIZE * DrawUtils.MPP * 0.90f, HALF_SIZE * DrawUtils.MPP * 0.90f);
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

        fixture.setFilterData(FILTER);

        return body;
    }

    @Override
    public void dispose() {
        this.shape.dispose();
    }
}
