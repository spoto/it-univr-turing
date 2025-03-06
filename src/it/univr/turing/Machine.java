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

/**
 * The general description of a deterministic Turing machine.
 */
public interface Machine {

	/**
	 * Yields the initial state of the machine.
	 * 
	 * @return the initial state
	 */
	int initialState();

	/**
	 * Determines if the given state is an acceptance state.
	 * 
	 * @param state the state
	 * @return true if and only if state is an acceptance state
	 */
	boolean accepts(int state);

	/**
	 * Yields the next information in the given state for the given head of the tape.
	 * 
	 * @param state the state
	 * @param head the head of the tape
	 * @return the next information (next state, replacement character and head movement direction)
	 * @throws RejectedException if there is no transition for the given state and tape head
	 */
	Next delta(int state, char head) throws RejectedException;

	/**
	 * The two possible head movement directions.
	 */
	enum Direction { L, R };

	/**
	 * The information in the transition table of the machine: next state, replacement character and
	 * tape head movement direction.
	 */
	record Next(int qPrime, char bPrime, Direction direction) {}
}