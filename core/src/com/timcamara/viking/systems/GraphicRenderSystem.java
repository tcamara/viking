package com.timcamara.viking.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.timcamara.viking.components.Graphic;
import com.timcamara.viking.components.Position;

public class GraphicRenderSystem extends EntitySystem {
    private ComponentMapper<Position> pm;
    private ComponentMapper<Graphic>  sm;
    private Viewport                  viewport;
    private SpriteBatch               batch;

    public GraphicRenderSystem(Viewport viewport) {
        super(Aspect.getAspectForAll(Position.class, Graphic.class));
        this.viewport = viewport;
    }

    @Override
    protected void initialize() {
        batch = new SpriteBatch();
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entities) {
        for(int i = 0; i < entities.size(); i++) {
            process(entities.get(i));
        }
    }

    @Override
    protected void begin() {
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
    }

    protected void process(Entity e) {
        Position position = pm.get(e);
        Graphic graphic = sm.get(e);

        // Set position, then call the draw method in the graphic component
        graphic.setPosition(position.x, position.y, Gdx.graphics.getDeltaTime());
        graphic.draw(batch, e);
    }

    @Override
    protected void end() {
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
