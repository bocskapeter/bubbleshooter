package eu.bopet.bubbleshooter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.badlogic.gdx.Gdx.gl20;

public class Room {
    private float width;
    private float height;
    private float length;
    private Environment environment;
    private Array<ModelInstance> bubbles = new Array<>();
    private ModelInstance modelInstance;
    private List<Color> colors = new ArrayList<>();

    private Vector3 origin;
    private Vector3 pointA;
    private Vector3 pointB;
    private Vector3 pointC;

    private Vector3 pointE;
    private Vector3 pointF;
    private Vector3 pointG;
    private Vector3 pointH;

    public Room(float width, float height, float length, float radius, Environment environment) {
        this.width = width;
        this.height = height;
        this.length = length;

        this.environment = environment;

        setEnvironment();

        populateColors();

        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        MeshPartBuilder builder = modelBuilder.part("line", 1, 3, new Material());

        gl20.glLineWidth(5);

        origin = new Vector3(0, 0, 0);
        pointA = new Vector3(width, 0, 0);
        pointB = new Vector3(width, height, 0);
        pointC = new Vector3(0, height, 0);

        pointE = new Vector3(width, 0, length);
        pointF = new Vector3(width, height, length);
        pointG = new Vector3(0, height, length);
        pointH = new Vector3(0, 0, length);

        builder.setColor(Color.RED);
        builder.line(origin, pointA);
        builder.line(pointA, pointB);
        builder.line(pointC, origin);

        builder.setColor(Color.BLUE);
        builder.line(pointE, pointF);
        builder.line(pointF, pointG);
        builder.line(pointG, pointH);
        builder.line(pointH, pointE);

        builder.setColor(Color.GREEN);
        builder.line(origin, pointH);
        builder.line(pointA, pointE);
        builder.line(pointB, pointF);
        builder.line(pointC, pointG);

        this.modelInstance = new ModelInstance(modelBuilder.end());

        int bubblesX = (int) (width / radius / 2);
        int bubblesZ = (int) (length / radius / 2);

        Vector3 position = new Vector3(-width / 2, (-2f / 3f) * height, 1f / 2f * length);

        for (int i = 0; i < bubblesX; i++) {
            for (int j = 0; j < bubblesZ; j++) {
                Vector3 bubblePosition = new Vector3(radius + i * 2 * radius, radius, radius + j * 2 * radius);
                bubblePosition.set(bubblePosition.add(position));
                Bubble bubble = new Bubble(radius, bubblePosition, getRandomColor());
                bubbles.add(bubble.getModelInstance());
            }
        }

        for (int i = 0; i < bubblesX; i++) {
            for (int j = 0; j < bubblesZ; j++) {
                Vector3 bubblePosition = new Vector3(radius + i * 2 * radius, 3 * radius, radius + j * 2 * radius);
                bubblePosition.set(bubblePosition.add(position));
                Bubble bubble = new Bubble(radius, bubblePosition, getRandomColor());
                bubbles.add(bubble.getModelInstance());
            }
        }

        this.modelInstance.transform.setTranslation(position);


    }

    private void setEnvironment() {
        this.environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.5f, 0.5f, 0.5f, 1f));
        this.environment.add(new DirectionalLight().set(0.9f, 0.9f, 0.9f, -1f, -0.8f, -0.2f));
    }

    private Color getRandomColor() {
        Random random = new Random();
        return this.colors.get(random.nextInt(colors.size()));
    }

    private void populateColors() {
        this.colors.add(Color.BLUE);
        this.colors.add(Color.GREEN);
        this.colors.add(Color.RED);
        this.colors.add(Color.YELLOW);
        this.colors.add(Color.CYAN);
        this.colors.add(Color.BROWN);
    }

    public ModelInstance getModelInstance() {
        return modelInstance;
    }

    public Array<ModelInstance> getBubbles() {
        return bubbles;
    }
}
