/* eslint-disable no-param-reassign */
function bfs(rGraph: number[][], start: number, end: number, parent: number[]) {
  // Create a visited array and mark all
  // vertices as not visited

  const visited = new Array(graph.length);
  for (let i = 0; i < rGraph.length; ++i) visited[i] = false;

  // Create a queue, enqueue source vertex
  // and mark source vertex as visited
  const queue = [];
  queue.push(start);
  visited[start] = true;
  parent[start] = -1;

  // Standard BFS Loop
  while (queue.length !== 0) {
    const line = queue.shift();

    for (let column = 0; column < rGraph.length; column++) {
      if (visited[column] === false && rGraph[line][column] > 0) {
        // If we find a connection to the sink
        // node, then there is no point in BFS
        // anymore We just have to set its parent
        // and can return true
        if (column === end) {
          parent[column] = line;

          return true;
        }
        queue.push(column);
        parent[column] = line;
        visited[column] = true;
      }
    }
  }

  // We didn't reach sink in BFS starting
  // from source, so return false
  return false;
}

function fordFulkerson(graph: number[][], start: number, end: number) {
  const rGraph = new Array(graph.length);

  for (let line = 0; line < graph.length; line++) {
    rGraph[line] = new Array(graph.length);

    for (let column = 0; column < graph.length; column++) {
      rGraph[line][column] = graph[line][column];
    }
  }
  // Armazena o caminho do BFS
  const parent = new Array(graph.length);
  let maxFlow = 0;

  while (bfs(rGraph, start, end, parent)) {
    let pathFlow = Number.MAX_VALUE;

    for (let column = end; column !== start; column = parent[column]) {
      const line = parent[column];
      pathFlow = Math.min(pathFlow, rGraph[line][column]);
    }

    for (let column = end; column !== start; column = parent[column]) {
      const line = parent[column];
      rGraph[line][column] -= pathFlow;
      rGraph[column][line] += pathFlow;
    }
    maxFlow += pathFlow;
  }

  return maxFlow;
}

export { fordFulkerson };

const graph = [
  [0, 16, 13, 0, 0, 0],
  [0, 0, 10, 12, 0, 0],
  [0, 4, 0, 0, 14, 0],
  [0, 0, 9, 0, 0, 20],
  [0, 0, 0, 7, 0, 4],
  [0, 0, 0, 0, 0, 0],
];
console.log(fordFulkerson(graph, 0, 5));
