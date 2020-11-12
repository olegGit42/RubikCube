package cube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;
import rubick.rubick.RubickMain;

@Data
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Cube implements Cloneable {
	@Autowired
	@Qualifier("sideWhite")
	Side sideFront;
	@Autowired
	@Qualifier("sideRed")
	Side sideDown;
	@Autowired
	@Qualifier("sideGreen")
	Side sideLeft;
	@Autowired
	@Qualifier("sideOrange")
	Side sideUp;
	@Autowired
	@Qualifier("sideBlue")
	Side sideRight;
	@Autowired
	@Qualifier("sideYellow")
	Side sideBack;

	public Cube() {
	}

	public Cube(Side sideFront, Side sideDown, Side sideLeft, Side sideUp, Side sideRight, Side sideBack) {
		this.sideFront = sideFront;
		this.sideDown = sideDown;
		this.sideLeft = sideLeft;
		this.sideUp = sideUp;
		this.sideRight = sideRight;
		this.sideBack = sideBack;
	}

	@Override
	public Cube clone() {
		return new Cube(sideFront.clone(), sideDown.clone(), sideLeft.clone(), sideUp.clone(), sideRight.clone(),
				sideBack.clone());
	}

	public static Cube getNewInstance() {
		return RubickMain.appContext.getBean("cube", Cube.class);
	}

}
