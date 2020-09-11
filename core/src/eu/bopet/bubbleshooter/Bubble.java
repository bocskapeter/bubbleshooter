package eu.bopet.bubbleshooter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class Bubble {
    private Vector3 position;
    private Color color;
    private float radius;
    private ModelInstance modelInstance;

    public Bubble(float radius, Vector3 position, Color color) {
        this.position = position;
        this.color = color;
        this.radius = radius;
        ModelBuilder modelBuilder = new ModelBuilder();
        this.modelInstance = new ModelInstance(modelBuilder.createSphere(2 * radius, 2 * radius, 2 * radius,
                (int) (5 * radius), (int) (5 * radius),
                new Material(ColorAttribute.createDiffuse(color)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal));
        this.modelInstance.transform.setTranslation(position);
    }

    public ModelInstance getModelInstance() {
        return modelInstance;
    }
}
