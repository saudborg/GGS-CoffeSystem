package com.sauloborges.ggs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sauloborges.ggs.domain.CoffeeType;
import com.sauloborges.ggs.domain.PaymenthMethod;
import com.sauloborges.ggs.domain.Programmer;
import com.sauloborges.ggs.domain.Statistic;
import com.sauloborges.ggs.repository.ProgrammerRepository;
import com.sauloborges.ggs.repository.StatisticRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class })
public class RepositoryTest {

	private ProgrammerRepository programmerRepository;

	private StatisticRepository statisticRepository;

	@Autowired
	public void setProgrammerRepository(ProgrammerRepository programmerRepository) {
		this.programmerRepository = programmerRepository;
	}

	@Autowired
	public void setStatisticRepository(StatisticRepository statisticRepository) {
		this.statisticRepository = statisticRepository;
	}

	@Test
	public void testSaveProgrammer() throws Exception {
		Programmer programmer = createAProgrammer("Test 1");

		Programmer baseProgrammer = programmerRepository.findOne(programmer.getId());

		assertNotNull(baseProgrammer);

		assertEquals(programmer.getId(), baseProgrammer.getId());
		assertEquals(programmer.getName(), baseProgrammer.getName());
		assertNotNull(baseProgrammer.getTimeStarted());
		assertEquals(programmer.getTimeStarted(), baseProgrammer.getTimeStarted());

	}

	private Programmer createAProgrammer(String name) {
		Programmer programmer = new Programmer(name);
		programmer.setCoffeType(CoffeeType.CAPPUCCINO);
		programmer.setPaymenthMethod(PaymenthMethod.CASH);
		programmer.setTimeStarted(Calendar.getInstance().getTimeInMillis());

		assertNull(programmer.getId());
		programmerRepository.save(programmer);
		assertNotNull(programmer.getId());
		return programmer;
	}

	@Test
	public void testSaveStatistic() {
		Programmer programmer = createAProgrammer("Test 1");

		Statistic statistic = new Statistic("MachineTest 1", programmer);
		assertNull(statistic.getId());

		statisticRepository.save(statistic);
		assertNotNull(statistic.getId());

		Statistic baseStatistic = statisticRepository.findOne(statistic.getId());
		assertNotNull(baseStatistic);

		assertEquals(statistic.getId(), baseStatistic.getId());
		assertEquals(statistic.getMachine(), baseStatistic.getMachine());
		assertEquals(programmer.getId(), baseStatistic.getProgrammer().getId());
	}

	@Test
	public void testLoadStatistic() {
		String nameMachine = "MachineTest 1";
		String nameMachine2 = "MachineTest 2";

		Programmer programmer = createAProgrammer("Test 1");

		Statistic statistic = new Statistic(nameMachine, programmer);
		statisticRepository.save(statistic);
		Statistic statistic2 = new Statistic(nameMachine2, programmer);
		statisticRepository.save(statistic2);

		Programmer programmer2 = createAProgrammer("Test 2");

		Statistic statistic4 = new Statistic(nameMachine, programmer2);
		statisticRepository.save(statistic4);

		List<Statistic> findStatisticByMachine1 = statisticRepository.findStatisticByMachine(nameMachine);
		assertNotNull(findStatisticByMachine1);
		assertEquals(findStatisticByMachine1.size(), 2);

		List<Statistic> findStatisticByMachine2 = statisticRepository.findStatisticByMachine(nameMachine2);
		assertNotNull(findStatisticByMachine2);
		assertEquals(findStatisticByMachine2.size(), 1);

	}

	@Test
	public void testNameMachines() {
		String nameMachine = "MachineTest 1";
		String nameMachine2 = "MachineTest 2";
		String nameMachine3 = "MachineTest 3";
		String coffeMachine = "coffeMachineTest 1";

		Programmer programmer = createAProgrammer("Test 1");
		Statistic statistic = new Statistic(nameMachine, programmer);
		statisticRepository.save(statistic);
		Statistic statistic2 = new Statistic(nameMachine2, programmer);
		statisticRepository.save(statistic2);
		Statistic statistic3 = new Statistic(nameMachine3, programmer);
		statisticRepository.save(statistic3);
		Statistic statistic4 = new Statistic(coffeMachine, programmer);
		statisticRepository.save(statistic4);

		List<String> listNameMachine = statisticRepository.getListNameMachine("MachineTest");
		assertNotNull(listNameMachine);
		assertEquals(3, listNameMachine.size());
		assertFalse(listNameMachine.contains(coffeMachine));

		List<String> listNameMachine2 = statisticRepository.getListNameMachine("coffeMachineTest");
		assertNotNull(listNameMachine2);
		assertEquals(1, listNameMachine2.size());
		assertEquals(coffeMachine, listNameMachine2.get(0));

	}

}
