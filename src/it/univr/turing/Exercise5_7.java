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
 * Exercise 5.7 of Pierre Wolper, "Introduction à la Calculabilité", 3rd edition.
 */
public class Exercise5_7 extends AbstractMachine {

	public static void main(String[] args) throws RejectedException {
		new Exercise5_7().askForInputAndExecute();
	}

	@Override
	public int initialState() {
		return 0;
	}

	@Override
	public boolean accepts(int state) {
		return false;
	}

	@Override
	public Next delta(int state, char head) throws RejectedException {
		switch (state) {
		case 0:
			switch (head) {
			case '0': return new Next(1, 'A', R);
			case '1': return new Next(1, 'B', R);
			default: break;
			}
			break;
		case 1:
			switch (head) {
			case '0': return new Next(1, '0', R);
			case '1': return new Next(1, '1', R);
			case 'x': return new Next(2, '0', R);
			case '#': return new Next(4, '#', L);
			default: break;
			}
			break;
		case 2:
			switch (head) {
			case '0': return new Next(3, 'x', L);
			case '1': return new Next(7, 'x', L);
			case '#': return new Next(3, '#', L);
			default: break;
			}
			break;
		case 3:
			switch (head) {
			case '0': return new Next(3, '0', L);
			case '1': return new Next(3, '1', L);
			case 'A': return new Next(0, '0', R);
			case 'B': return new Next(0, '1', R);
			case 'C': return new Next(6, '0', R);
			default: break;
			}
			break;
		case 4:
			switch (head) {
			case '0': return new Next(4, '0', L);
			case '1': return new Next(4, '1', L);
			case 'A': return new Next(5, '0', R);
			case 'B': return new Next(5, '1', R);
			case 'C': return new Next(8, '0', R);
			default: break;
			}
			break;
		case 6: // q0'
			switch (head) {
			case '0': return new Next(1, 'B', R);
			case '1': return new Next(1, 'C', R);
			default: break;
			}
			break;
		case 7: // q3'
			switch (head) {
			case '0': return new Next(7, '0', L);
			case '1': return new Next(7, '1', L);
			case 'A': return new Next(0, '1', R);
			case 'B': return new Next(6, '0', R);
			case 'C': return new Next(6, '1', R);
			default: break;
			}
			break;
		case 8: // q5'
			switch (head) {
			case '0': return new Next(5, '1', R);
			case '1': return new Next(8, '0', R);
			default: break;
			}
			break;
		}

		throw new RejectedException("There is no transition from state = " + state + " and head = " + head);
	}
}