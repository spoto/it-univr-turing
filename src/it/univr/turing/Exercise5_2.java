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

import static it.univr.turing.Machine.Direction.*;

/**
 * Exercise 5.2 of Pierre Wolper, "Introduction à la Calculabilité", 3rd edition.
 */
public class Exercise5_2 extends AbstractMachine {

	public static void main(String[] args) throws RejectedException {
		new Exercise5_2().askForInputAndExecute();
	}

	@Override
	public int initialState() {
		return 0;
	}

	@Override
	public boolean accepts(int state) {
		return state == 6;
	}

	@Override
	public Next delta(int state, char head) throws RejectedException {
		switch (state) {
		case 0:
			switch (head) {
			case 'a': return new Next(1, 'A', R);
			case 'B': return new Next(4, 'B', R);
			case 'b': return new Next(5, 'b', R);
			default: break;
			}
			break;
		case 1:
			switch (head) {
			case 'a': return new Next(1, 'a', R);
			case 'b': return new Next(3, 'B', L);
			case 'B': return new Next(2, 'B', R);
			default: break;
			}
			break;
		case 2:
			switch (head) {
			case 'B': return new Next(2, 'B', R);
			case 'b': return new Next(3, 'B', L);
			default: break;
			}
			break;
		case 3:
			switch (head) {
			case 'a': return new Next(3, 'a', L);
			case 'B': return new Next(3, 'B', L);
			case 'A': return new Next(0, 'A', R);
			default: break;
			}
			break;
		case 4:
			switch (head) {
			case 'B': return new Next(4, 'B', R);
			case 'b': return new Next(5, 'b', R);
			default: break;
			}
			break;
		case 5:
			switch (head) {
			case 'b': return new Next(5, 'b', R);
			case '#': return new Next(6, '#', R);
			default: break;
			}
			break;
		}

		throw new RejectedException("There is no transition from state = " + state + " and head = " + head);
	}
}