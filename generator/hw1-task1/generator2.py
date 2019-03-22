import random
import sys

def create_tree(n):
  parent = []
  parent.append(1)
  children = []
  for i in range(1, n):
      children.append(parent[random.randint(0,len(parent)-1)])
      parent.append(i)
  return children


if __name__ == '__main__':
  n = int(sys.argv[1])
  
  #setting seed if it was passed, otherwise use default
  if len(sys.argv) == 2:
    s = 7
  else:
    s = int(sys.argv[2])

  random.seed(s)
  print(n)
  print(" ".join(map(lambda x: str(x),create_tree(n))))
  test_cases = random.randint(1,10)
  print(test_cases)
  for i in range(0, test_cases):
    print("%d %d" % (random.randint(1,n), random.randint(0,100000)))

