import random

random.seed(42)

chars = "abcdefghijklmnopqrstvuwxyz0123456789"

out = ""
for i in range(0, 100000):
    out = out + chars[random.randint(0,len(chars) - 1)]

print(out)
