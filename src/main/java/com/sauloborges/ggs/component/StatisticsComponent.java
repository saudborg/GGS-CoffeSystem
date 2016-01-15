package com.sauloborges.ggs.component;

import static com.sauloborges.ggs.util.Utils.formatTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sauloborges.ggs.domain.CoffeeType;
import com.sauloborges.ggs.domain.PaymenthMethod;
import com.sauloborges.ggs.domain.Programmer;
import com.sauloborges.ggs.domain.Statistic;
import com.sauloborges.ggs.receiver.GetCoffeeInMachineReceiver;
import com.sauloborges.ggs.repository.ProgrammerRepository;
import com.sauloborges.ggs.repository.StatisticRepository;

/**
 * Class represent the results of the challenge
 * @author sauloborges
 *
 */
@Component
public class StatisticsComponent {

	@Autowired
	private StatisticRepository statisticRepository;

	@Autowired
	private ProgrammerRepository programmerRepository;
	
	/**
	 * Variable to set if the statistics is already done
	 */
	private boolean statsDone = false;

	/**
	 * This method will show how much coffee is sold (total and per payment type)
	 * @return
	 */
	public String howMuchCoffeIsSold() {
		int totalCoffeSold = 0;
		int totalPaidInCash = 0;
		int totalPaidInCard = 0;
		
		List<Programmer> programmers = programmerRepository.findAll();
		for (Programmer programmer : programmers) {
				totalCoffeSold++;
				if (programmer.getPaymenthMethod() == PaymenthMethod.CASH) {
					totalPaidInCash++;
				} else {
					totalPaidInCard++;
				}
		}

		StringBuffer sb = new StringBuffer();
		sb.append("\n\n########################################################################");
		sb.append("\nHow much coffee is sold? (Total and per payment type)\n");
		sb.append("_________________________________________________________________________");
		sb.append("\nTotal Coffee Sold: " + totalCoffeSold);
		sb.append("\nTotal Coffee Paid In Cash: " + totalPaidInCash);
		sb.append("\nTotal Coffee Paid In Card: " + totalPaidInCard);
		sb.append("\n\n########################################################################\n");

		return sb.toString();

	}

	/**
	 * This method will show how much coffee is dispensed by each coffee machine (total and per type of coffee)
	 * @return
	 */
	public String howMuchCoffeIsDispensedByEachCoffeeMachine() {

		List<String> listNameMachine = statisticRepository.getListNameMachine(GetCoffeeInMachineReceiver.class.getName());
		List<Statistic> list = statisticRepository.findStatisticByMachine(GetCoffeeInMachineReceiver.class.getName());

		String nameMachine1 = listNameMachine.get(0);

		int coffeeMachine1Total = 0;
		int coffeeMachine2Total = 0;

		int coffeeMachine1Espresso = 0;
		int coffeeMachine1Latte = 0;
		int coffeeMachine1Cappuccino = 0;

		int coffeeMachine2Espresso = 0;
		int coffeeMachine2Latte = 0;
		int coffeeMachine2Cappuccino = 0;

		for (Statistic statistic : list) {
			if (statistic.getMachine().equals(nameMachine1)) {
				coffeeMachine1Total++;
				if (statistic.getProgrammer().getCoffeType().equals(CoffeeType.ESPRESSO)) {
					coffeeMachine1Espresso++;
				} else if (statistic.getProgrammer().getCoffeType().equals(CoffeeType.CAPPUCCINO)) {
					coffeeMachine1Cappuccino++;
				} else {
					coffeeMachine1Latte++;
				}
			} else {
				coffeeMachine2Total++;
				if (statistic.getProgrammer().getCoffeType().equals(CoffeeType.ESPRESSO)) {
					coffeeMachine2Espresso++;
				} else if (statistic.getProgrammer().getCoffeType().equals(CoffeeType.CAPPUCCINO)) {
					coffeeMachine2Cappuccino++;
				} else {
					coffeeMachine2Latte++;
				}
			}
		}

		StringBuffer sb = new StringBuffer();
		sb.append("\n\n########################################################################");
		sb.append("\nHow much coffe is dispensed by each coffee machine (total and per type)\n");
		sb.append("_________________________________________________________________________");
		sb.append("\n Machine 1: \t TOTAL = " + coffeeMachine1Total);
		sb.append("\n\t\t ESPRESSO = " + coffeeMachine1Espresso);
		sb.append("\n\t\t CAPPUCCINO = " + coffeeMachine1Cappuccino);
		sb.append("\n\t\t LATTE = " + coffeeMachine1Latte);
		sb.append("\n");
		sb.append("\n Machine 2: \t TOTAL = " + coffeeMachine2Total);
		sb.append("\n\t\t ESPRESSO = " + coffeeMachine2Espresso);
		sb.append("\n\t\t CAPPUCCINO = " + coffeeMachine2Cappuccino);
		sb.append("\n\t\t LATTE = " + coffeeMachine2Latte);
		sb.append("\n\n########################################################################\n");
		
		return sb.toString();
	}

	/**
	 * This method will show how much time does the average programmer spend getting her coffee
	 * @return
	 */
	public String howMuchTimeDoesTheAverageProgrammerSpendGettingHerCoffee() {

		List<Programmer> programmers = programmerRepository.findAll();

		int size = programmers.size();
		long total = 0;
		long spentInPayQueue = 0;
		long spentToPaidAndGotACoffee = 0;
		long spentInMachineCoffeeQueue = 0;
		long spentInCoffeeMachine = 0;

		for (Programmer programmer : programmers) {
			total = total + (programmer.getTimeFinished() - programmer.getTimeStarted());
			spentInPayQueue = spentInPayQueue + (programmer.getTimeLeavePayQueue() - programmer.getTimeEnterPayQueue());
			spentToPaidAndGotACoffee = spentToPaidAndGotACoffee
					+ (programmer.getTimeFinished() - programmer.getTimePaid());
			spentInMachineCoffeeQueue = spentInMachineCoffeeQueue
					+ (programmer.getTimeLeaveGetTheCoffeeQueue() - programmer.getTimeEnterGetTheCoffeeQueue());
			spentInCoffeeMachine = spentInCoffeeMachine
					+ (programmer.getTimeFinished() - programmer.getTimeStartedToGetTheCoffe());
		}

		StringBuffer sb = new StringBuffer();
		sb.append("\n\n########################################################################");
		sb.append("\nHow much time does the average programmer spend getting her coffe\n");
		sb.append("_________________________________________________________________________");
		sb.append("\nAverage: " + formatTime(total / size));
		sb.append("\nAverage in pay queue: " + formatTime(spentInPayQueue / size));
		sb.append("\nAverage in paid and got a coffee: " + formatTime(spentToPaidAndGotACoffee / size));
		sb.append("\nAverage in the machine coffee queue: " + formatTime(spentInMachineCoffeeQueue / size));
		sb.append("\nAverage to got the coffee in machine: " + formatTime(spentInCoffeeMachine / size));
		sb.append("\n\n########################################################################\n");
		
		return sb.toString();
	}

	/**
	 * This method will show how long did it take the fastest and the slowest programmer to get her coffee
	 * @return
	 */
	public String howLongDidItTakeTheFastestAndTheSlowestProgrammerToGetHerCoffee() {

		Programmer fastestGeral;
		Programmer slowestGeral;

		List<Programmer> programmers = programmerRepository.findAll();
		fastestGeral = programmers.get(0);
		slowestGeral = programmers.get(0);

		long timeFastest = fastestGeral.getTimeFinished() - fastestGeral.getTimeStarted();
		long timeSlowest = fastestGeral.getTimeFinished() - fastestGeral.getTimeStarted();
		
		for (int i = 1; i < programmers.size(); i++) {
			Programmer programmer = programmers.get(i);
			long time = programmer.getTimeFinished() - programmer.getTimeStarted();
			if(time < timeFastest){
				fastestGeral = programmer;
				timeFastest = time;
			}
			else if (time > timeSlowest){
				slowestGeral = programmer;
				timeSlowest = time;
			}
		}

		StringBuffer sb = new StringBuffer();
		sb.append("\n\n########################################################################");
		sb.append("\nHow long did it take the fastest and the slowest programmer to get her coffee\n");
		sb.append("_________________________________________________________________________");
		sb.append("\n Fastest Programmer: ");
		sb.append("\n\t\t name: " + fastestGeral.getName());
		sb.append("\n\t\t coffe: " + fastestGeral.getCoffeType());
		sb.append("\n\t\t paymenth method: " + fastestGeral.getPaymenthMethod());
		sb.append("\n\t\t time: " + formatTime(timeFastest));
		sb.append("\n");
		sb.append("\n Slowest Programmer: ");
		sb.append("\n\t\t name: " + slowestGeral.getName());
		sb.append("\n\t\t coffe: " + slowestGeral.getCoffeType());
		sb.append("\n\t\t paymenth method: " + slowestGeral.getPaymenthMethod());
		sb.append("\n\t\t time: " + formatTime(timeSlowest));
		sb.append("\n\n########################################################################\n");

		return sb.toString();

	}

	public boolean isStatsDone() {
		return statsDone;
	}

	public void setStatsDone(boolean statsDone) {
		this.statsDone = statsDone;
	}

}
