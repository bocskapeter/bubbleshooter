package eu.bopet.bubbleshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class BubbleShooterGame extends ApplicationAdapter {

    private Environment environment;
    private PerspectiveCamera cam;
    private CameraInputController camController;
    private ModelBatch modelBatch;
    private Array<ModelInstance> instances = new Array<>();

    private BitmapFont font;
    private SpriteBatch batch;

    private final Vector3 camPosition = new Vector3(0,15,-45f);

    @Override
    public void create() {

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2);

        batch = new SpriteBatch();

        modelBatch = new ModelBatch();
        cam = new PerspectiveCamera(50, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(camPosition);
        cam.up.set(0, 1, 0);
        cam.lookAt(0, 0, 0);

        cam.near = 1f;
        cam.far = 600f;
        cam.update();

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);

        environment = new Environment();

        Room room = new Room(40, 70, 70, 5, environment);

        instances.add(room.getModelInstance());
        instances.addAll(room.getBubbles());
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        cam.position.set(camPosition);

        cam.up.set(0, 1, 0);

        if (cam.direction.y>0) cam.direction.y = 0;
        if (cam.direction.y<-0.35f) cam.direction.y = -0.35f;

        if (cam.direction.x>0.15f) cam.direction.x = 0.15f;
        if (cam.direction.x<-0.15f) cam.direction.x = -0.15f;

        if (cam.direction.z > 0.51f) cam.direction.z =0.51f;
        if (cam.direction.z < 0.49f) cam.direction.z =0.49f;

        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();

        camController.update();

        batch.begin();
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 3, Gdx.graphics.getHeight() - 3);
        batch.end();

    }

    @Override
    public void dispose() {

    }
}
