package nl.eleven.adventofcode.models.cpu;

public interface RegisterInterface {

	void addRegister(int registerIndex, int value);

	int getRegisterValue(int registerIndex);
}
