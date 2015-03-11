# mini-cactpot

This program looks to do analysis on a board for a minigame in FFXIV:ARR called the mini-cactpot.

You are given a 3x3 matrix of numbers, filled with the numbers 1-9 in random positions, with no number repeated.  
One of the numbers is given to you at the start, and 3 more can be chosen to be revealed.  (For simplicity's sake, this
program assumes it will be given a matrix with 4 numbers revealed.)  

The player is then tasked to choose any row, column, or diagonal.  The resulting 3 numbers are added, and a payout is given
based on the sum.  Higher doesn't equate to better payout -- payouts look like this:
```
sum  payout
---  ------
6    10000
7    36
8    720
9    360
10   80
11   252
12   108
13   72
14   54
15   72
16   54
17   180
18   119
19   36
20   306
21   1080
22   144
23   1800
24   3600
```

This program tries to find the best (with variable definitions of best) row to choose given a board with 4 revealed.

Different output types:

- best risky row: highest possible payout -- gives the row with the highest average payout over all possibilities.  This
 will give a good idea of where the highest payout is, but ignores probablity of obtaining that payout.
- safest row: highest chance of > 200 payout -- gives the row with the most number of possibilites that have over 200 in payout.
  This is the consistent option.

TODOs:
------

- I'd very much like to try this using core.logic instead.

## Installation

(not currently functional, but:)  
git clone and lein jar

## Usage

args should be a vector of maps containing 4 different positions and their values.  
ex: java -jar mini-cactpot-0.1.0-standalone.jar "[{:val 2 :row 0 :col 0} {:val 1 :row 1 :col 1} {:val 7 :row 1 :col 2} {:val 3 :row 2 :col 2}]"

    $ java -jar mini-cactpot-0.1.0-standalone.jar [args]

## Options

.... None.  Don't pass it options.

## Examples

todo

### Bugs

todo

## License

Copyright © 2015 zekna

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
