"""
Name: pushGame.py
Description: Program to determine the value of a position in the combinatorial game Push
"""

# Imports
import copy
import sys

# Game represented with Ls (black hands) and Rs (white hands) in an array

class PushGame:
	def __init__(self, startingGame):
		self.game = startingGame
		self.values = {
			"L": 1,
			"R": -1,
			"_" : 0 
		}

	def getNext(self, indicies):
		positions = []
		# Find new Left positions
		for i in indicies:
			newPosition = copy.deepcopy(self.game)

			if i == 0:
				# on the left side of the board
				# 	just get rid of it
				newPosition[0] = ''
			elif i == len(self.game) - 1:
				# you are at the end of the game board
				# just shrink the game by one on the left
				newPosition = newPosition[1:]
			else:
				secondNewCopy = newPosition
				newPosition = secondNewCopy[1:i+1]
				newPosition.extend([""])
				newPosition.extend(secondNewCopy[i+1:])

			if newPosition != self.game:
				positions.append(PushGame(newPosition))

		return positions

	def produceMovesets(self):
		nextLPositions = []
		nextRPositions = []

		# list of indicies for right and left pieces
		rightPieces = []
		leftPieces = []

		for i in range(len(self.game)):
			if self.game[i].lower() == 'l':
				leftPieces.append(i)
			elif self.game[i].lower() == 'r':
				rightPieces.append(i)

		# Find new Left and Right positions, then return them in a tuple
		return self.getNext(leftPieces), self.getNext(rightPieces)

	def getValue(self, game):
		if str(game) in self.values:
			return self.values[str(game)]
		else:


	def getValueOfGame(self):
		# get list of PushGame objects that are the next positions from the current one
		nextL, nextR = self.produceMovesets()
		leftVals = []
		rightVals = []

		for pos in nextL:
			self.getValue(pos)

	def __str__(self):
		newGame = []

		for i in self.game:
			i = i.lower()
			if i == "":
				newGame.append('_')
			elif i == 'l':
				newGame.append('L')
			elif i == 'r':
				newGame.append('R')

		return "<" + "".join(newGame) + ">"

if __name__ == "__main__":
	if len(sys.argv) < 2:
		print("Usage: python pushGame.py <startingGame>")
		sys.exit(1)

	startingGame = sys.argv[1]
	testGame = PushGame(list(startingGame))