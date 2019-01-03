package biz.ostw.game.tank.obj;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import biz.ostw.game.tank.TextureFactory;
import biz.ostw.libgdx.DrawUtils;

public class LandscapeFactory extends ObjectFactory {

    protected static final float HALF_SIZE = 125;

    private final TextureRegion blockTextureRegion;

    private final TextureRegion riverTextureRegion;

    private final TextureRegion forestTextureRegion;

    private final TextureRegion[] bricksTextureRegions;

    private final BodyDef bodyDef;

    private final PolygonShape shape;

    {
        this.blockTextureRegion = TextureFactory.getRegion("landscape/block");
        this.riverTextureRegion = TextureFactory.getRegion("landscape/river");
        this.forestTextureRegion = TextureFactory.getRegion("landscape/forest");
        this.bricksTextureRegions = TextureFactory.getRegions("landscape/brick").items;

        this.bodyDef = new BodyDef();
        this.bodyDef.type = BodyDef.BodyType.StaticBody;

        this.shape = new PolygonShape();
        ((PolygonShape) shape).setAsBox(HALF_SIZE * DrawUtils.MPP, HALF_SIZE * DrawUtils.MPP);
    }

    @Override
    public Object[] supportedKeys() {
        return LandscapeType.values();
    }

    @Override
    public LandscapeElement build(final World world, final Object type, final Object... params) {
        final LandscapeElement object;

        switch ((LandscapeType) type) {
            case BLOCK:
                object = this.createBlock(world);
                break;

            case BRICK:
                object = this.createBrick(world, params);
                break;

            case RIVER:
                object = this.createRiver(world);
                break;

            case FOREST:
                object = this.createForest(world);
                break;

            default:
                throw new IllegalArgumentException();
        }

        return object;
    }

    private LandscapeElement createBlock(final World world) {
        final LandscapeElement element = new SingleLandscapeElement(this.createBody(world), this.blockTextureRegion);

        return element;
    }

    private LandscapeElement createBrick(final World world, final Object... params) {
        final LandscapeElement element;

        if (params != null && params.length > 0 && params[0] instanceof Number) {
            int index = ((Number) params[0]).intValue() % 2;

            element = new SingleLandscapeElement(this.createBody(world), this.bricksTextureRegions[index]);
        } else {
            throw new IllegalArgumentException();
        }

        return element;
    }

    private LandscapeElement createRiver(final World world) {
        final LandscapeElement element = new SingleLandscapeElement(this.createBody(world), this.riverTextureRegion);

        return element;
    }

    private LandscapeElement createForest(final World world) {
        final LandscapeElement element = new SingleLandscapeElement(this.createBody(world), this.forestTextureRegion);

        return element;
    }

    private Body createBody(World world) {
        Body body = world.createBody(this.bodyDef);
        body.setActive(true);
        Fixture fixture = body.createFixture(this.shape, 1f);

        return body;
    }

    @Override
    public void dispose() {
        this.shape.dispose();
    }
}
