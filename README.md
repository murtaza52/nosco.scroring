# Nosco Challenge

There is an innovation competition across the land, and the various
houses compete. All the ideas have been collected and scored, and now is the time
to calculate the results to find out the most innovative house.

## Scoring of ideas

Each idea is scored by a random group of people. The number of scorers is different for each idea. There might be only one scorer, or there might be many. Unfortunately some scores were lost and were replaced by nulls. These scores should be ignored entirely. Ideas with no scores
are entirely excluded from consideration. The total score of each idea is the average of all its scores.

## Houses

Ideas are submitted by people, and people are affiliated with houses, therefore ideas are  credited to houses.

Some people are affiliated with no house, in which case they should be counted
as if in the house 'Free folk'.

Some people are affiliated with more than one house, in which case the
idea is credited to each and every one of the affiliated houses. For example,
if user A is affiliated with houses X and Y, and they have submitted an idea that
has a total score of 5.7, both house X and Y will add a score of 5.7 in their tally.

## House innovation score

The innovation score of a house is the median of the scores of all the ideas
submitted by people affiliated with this house. Higher is better.

## Expected output

The innovation committee expects some results in order to make their
decision, but they don't know what format exactly they prefer. You are
therefore free to choose your own output format for the results.

The results should include:

- a list of the houses, from most innovative to least innovative
- the innovation score of each house
- the number of ideas submitted by each house


## Input Data

The input data is two JSON files, and their EDN counterparts (containing the same data).

`users.json` contains various users, each having an id first name,
last name, email, and potentially a list of their house
affiliation(s).

`ideas.json` contains various ideas, each one having an id,
a title, a body, an author-id (pointing to one entry in the `users.json` file)
and an array of numeric scores.

## Other considerations

The readability of the code is the most important metric. Use descriptive names and add docstrings and comments as needed. Performance is not a consideration as long as the total calculation finishes within a few seconds.

You're free to use any 3rd-party library that seems suitable for the
task, keeping in mind the readability constraint.

The solutions should include instructions on how to run it and get the results.

There's no need to write a test suite for this exercise -- this is not production code and it won't be judged as such.

Please excude the outdated popular reference.
