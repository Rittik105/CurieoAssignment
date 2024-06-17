import random

initial_reserve = 5000
n_transactions = 500000
n_queries = 100000
task_type = 1

def generate_inputs():
    file = open("input.txt", "w")

    file.write(str(initial_reserve) + " " + str(n_transactions)+ " " + str(n_queries)+"\n")

    curr_time = 0

    transactions = []
    for i in range(n_transactions):
        curr_time += random.randint(1, 10000)

        tr_type = random.randint(1, 2)
        if tr_type == 1:
            tr = "Deposit"
        else:
            tr = "Withdraw"

        amount = random.randint(100, 100000)

        transactions.append(str(curr_time) + " " + tr + " " + str(amount))
    
    queries = []
    for i in range(n_queries):
        start_time = random.randint(0, curr_time)
        end_time = random.randint(start_time, curr_time+1)

        queries.append("Query" + " " + str(start_time) + " " + str(end_time))

    if task_type == 1:
        for transaction in transactions:
            file.write(transaction + "\n")
        for query in queries:
            file.write(query + "\n")
    else:
        commands = [x.pop(0) for x in random.sample(
            [transactions]*len(transactions) + [queries]*len(queries), len(transactions)+len(queries))]
        
        for command in commands:
            file.write(command + "\n")

    file.close()

generate_inputs()
