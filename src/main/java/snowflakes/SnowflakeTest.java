package snowflakes;

import java.util.function.BiConsumer;

import javax.media.opengl.GL;

import reusable.graphicsPrimitives.Vec2;

public class SnowflakeTest extends Snowflake
{

	public SnowflakeTest(Vec2 position, double angle, BiConsumer<Double, Snowflake> updateFunc)
	{
		super(position, angle);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void internalDraw(GL gl)
	{
		// TODO Auto-generated method stub
		
	}

}
