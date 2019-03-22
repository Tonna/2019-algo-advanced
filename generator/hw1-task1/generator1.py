import random
import sys

def next_val(n, pos):
  val = random.randint(1,n);
  if val == pos:
      return next_val(n, pos)
  else:
      return val

def generate_data(n):
  out = []
  for i in range(2,n+1):
    out.append(next_val(n,i))
  return out

def contains_cycle(lis):
  to_check = []
  count = 2
  for i in lis:
    to_check.append((i, count))
    count = count + 1
  for i in to_check:
      for j in to_check:
          if i == (j[1],j[0]):
              return True
  return False

def not_tree(lis):
  to_check = []
  count = 2
  for i in lis:
    to_check.append((i, count))
    count = count + 1
  for i in to_check:
      for j in to_check:
          if i[0] == j[1]:
              continue
          return True
  return False

def generate_tree_input(n):
  data = generate_data(n)
  if contains_cycle(data):
    data = generate_data(n)
  #if not_tree(data):
  #  print("bbbb")
  return " ".join(map(lambda x: (str(x)), data))


if __name__ == '__main__':
  n = int(sys.argv[1])
  print(n)
  print(generate_tree_input(n))
  print("1")
  print("1 1")


