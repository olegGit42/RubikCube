package cube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Cube {
	@Autowired
	@Qualifier("sideW")
	Side sideF;
	@Autowired
	@Qualifier("sideR")
	Side sideD;
	@Autowired
	@Qualifier("sideG")
	Side sideL;
	@Autowired
	@Qualifier("sideO")
	Side sideU;
	@Autowired
	@Qualifier("sideB")
	Side sideR;
	@Autowired
	@Qualifier("sideY")
	Side sideB;

	public Cube() {
	}

	/*
	 * public Cube(Side side1, Side side2, Side side3, Side side4, Side side5, Side
	 * side6) { this.sideF = side1; this.sideD = side2; this.sideL = side3;
	 * this.sideU = side4; this.sideR = side5; this.sideB = side6; }
	 */

}
