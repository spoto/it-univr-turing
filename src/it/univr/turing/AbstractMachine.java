/*
Copyright 2025 Fausto Spoto

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package it.univr.turing;

import java.util.Scanner;

/**
 * Partial implementation of a deterministic Turing machine.
 */
public abstract class AbstractMachine implements Machine {

	/**
	 * Yields the initial configuration for the given input string.
	 * 
	 * @param w the input string
	 * @return the initial configuration
	 */
	private Configuration initial(String w) {
		return new Configuration(initialState(), "", w);
	}

	/**
	 * Performs all transitions from the given input string, until an acceptance
	 * state is reached or no ore transitions can be executed further.
	 * 
	 * @param w the input string
	 * @throws RejectedException if no more transitions can be executed further and no acceptance state has been reached
	 */
	private void execute(String w) throws RejectedException {
		Configuration current;
		int step = 1;

		for (current = initial(w); !accepts(current.state); current = current.apply(delta(current.state, current.head())))
			System.out.println(step++ + ": " + current);

		System.out.println(step + ": " + current);
	}

	/**
	 * Reads the input work from the keyboard and executes the transitions from that word.
	 * 
	 * @throws RejectedException if no more transitions can be executed further and no acceptance state has been reached
	 */
	protected final void askForInputAndExecute() throws RejectedException {
		try (Scanner keyboard = new Scanner(System.in)) {
			System.out.print("Insert an input word: ");
			String w = keyboard.nextLine();
			execute(w);
		}
	}

	/**
	 * A configuration of a Turing machine: current state, string on the left of the
	 * tape head, string from the tape head to its right.
	 */
	private record Configuration(int state, String before, String after) {

		@Override
		public final String toString() {
			return "(" + state + "," + before + "," + after + ")";
		}

		/**
		 * Yields the character below the tape head.
		 * 
		 * @return the character below the tape head
		 */
		private char head() {
			return after.isEmpty() ? '#' : after.charAt(0);
		}

		/**
		 * Yields the configuration resulting from the application of the given
		 * next state, replacement character and tape head movement direction to this configuration.
		 * 
		 * @param next the next state, replacement character and tape head movement direction
		 * @return the resulting configuration
		 * @throws RejectedException if there is no configuration resulting from the application of next
		 */
		private Configuration apply(Next next) throws RejectedException {
			char bPrime = next.bPrime();
			int qPrime = next.qPrime();

			if (next.direction() == Direction.L) {
				if (before.isEmpty())
					throw new RejectedException("Cannot move further to the left of the tape");

				String alpha1Prime = before.substring(0, before.length() - 1);
				char a = before.charAt(before.length() - 1);

				if (after.isEmpty())
					return new Configuration(qPrime, alpha1Prime, a + "" + bPrime);
				else {
					String alpha2Prime = after.substring(1); 
					return new Configuration(qPrime, alpha1Prime, a + (bPrime + alpha2Prime));
				}
			}
			else { // next.direction() == Direction.R
				String alpha1 = before;

				if (after.isEmpty())
					return new Configuration(qPrime, alpha1 + bPrime, "");
				else {
					String alpha2 = after.substring(1);
					return new Configuration(qPrime, alpha1 + bPrime, alpha2);
				}
			}
		}
	}
}