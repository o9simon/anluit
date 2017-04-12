package anluit.cg.generator.action;

public class WakeUpActionGenerator extends ActionGenerator {

	@Override
	public String[] generate() {
		return new String[]{"getDevice().wakeUp();"};
	}

}
