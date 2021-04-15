# Post QuickPoll
http://localhost:8080/polls
{
"question": "Who will win SuperBowl this year?",
"options": [
{"value": "New England Patriots"},
{"value": "Seattle Seahawks"},
{"value": "Green Bay Packers"},
{"value": "Denver Broncos"}]
}

# Get Poll
http://localhost:8080/polls/1

# Get all Votes
http://localhost:8080/polls/1/votes


# Modified Poll
{
"question": "Who will win SuperBowl this year?",
"options": [
{"id": 1, "value": "New England Patriots"},
{"id": 2, "value": "Seattle Seahawks"},
{"id": 3, "value": "Green Bay Packers"},
{"id": 4, "value": "Denver Broncos"}]
}
