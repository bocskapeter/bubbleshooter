package eu.bopet.bubbleshooter;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

import static com.badlogic.gdx.Gdx.gl20;

public class Path {
    private Vector3 position;
    private Vector3 direction;
    private Environment environment;
    private ModelInstance modelInstance;

    public Path(Vector3 position, Environment environment, ModelInstance modelInstance) {
        this.position = position;
        this.environment = environment;
        this.modelInstance = modelInstance;
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        MeshPartBuilder builder = modelBuilder.part("line", 1, 3, new Material());
        gl20.glLineWidth(5);
    }


}
