import random

def seq(n):
    a = "0"
    for k in range(1, n+1):
        for i in a:
            if i == "0":
                a = a + "1"
            else:
                a = a + "0"
    return a

random.seed(42)

n = 10**5
#get 100 000 long string
s = list(seq(21)[:n])
assert(len(s) == n)

#add noise
p = list(range(n))
random.shuffle(p)
for i in range(random.randint(300,500)):
    s[p[i]] = "0" if s[p[i]] == "1" else "1"

print("".join(s)
