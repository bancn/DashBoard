import java.util.*;

public class LibrarySorter {
    
    public List<String> sortLibrary(Map<String, Set<String>> libraries) {
        List<String> sortedList = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Set<String> inStack = new HashSet<>();
        
        for (String library : libraries.keySet()) {
            if (!visited.contains(library)) {
                if (!dfs(library, libraries, visited, inStack, sortedList)) {
                    throw new IllegalArgumentException("Cycle detected in dependencies.");
                }
            }
        }
        
        Collections.reverse(sortedList);  // reverse the list to get the correct order
        return sortedList;
    }
    
    private boolean dfs(String current, Map<String, Set<String>> libraries, Set<String> visited, Set<String> inStack, List<String> sortedList) {
        if (inStack.contains(current)) {
            return false;  // cycle detected
        }
        if (visited.contains(current)) {
            return true;  // already processed this node
        }

        visited.add(current);
        inStack.add(current);
        
        for (String dependency : libraries.getOrDefault(current, Collections.emptySet())) {
            if (!dfs(dependency, libraries, visited, inStack, sortedList)) {
                return false;  // cycle detected in recursion
            }
        }
        
        inStack.remove(current);
        sortedList.add(current);
        return true;
    }

    public static void main(String[] args) {
        Map<String, Set<String>> libraries = new HashMap<>();
        libraries.put("A", new HashSet<>(Arrays.asList("B", "C")));
        libraries.put("B", new HashSet<>(Collections.singletonList("D")));
        libraries.put("C", new HashSet<>(Collections.singletonList("D")));
        libraries.put("D", new HashSet<>(Collections.singletonList("E")));
        libraries.put("E", new HashSet<>());

        LibrarySorter sorter = new LibrarySorter();
        List<String> sortedLibraries = sorter.sortLibrary(libraries);

        System.out.println("Sorted Libraries: " + sortedLibraries);
    }
}


解决有依赖关系的排序问题，特别是在图论中，这被称为拓扑排序（Topological Sorting）。拓扑排序是一种线性排序算法，主要用于有向无环图（DAG，Directed Acyclic Graph）中，使得对每一条有向边 (u, v)，顶点 u 在顶点 v 之前出现。它广泛应用于解决诸如任务调度、编译器依赖解析等问题。以下是解决这种排序问题的思路和步骤：

思路
构建图：

将所有的库视为图的顶点（节点）。
将每个库的依赖关系视为有向边（从当前库指向其依赖的库）。
检测环：

如果依赖关系中存在环（循环依赖），则无法进行拓扑排序。需要首先检测并处理循环依赖。
选择排序算法：

使用深度优先搜索（DFS）或者广度优先搜索（BFS）来实现拓扑排序。
DFS 方法通过递归的方式处理每个顶点，完成后将顶点加入结果列表中。
BFS 方法使用入度表（记录每个顶点的入度）和队列，逐层剔除入度为 0 的顶点。
实现步骤（DFS）
初始化数据结构：

一个 visited 集合记录已访问的节点。
一个 inStack 集合用于检测循环依赖。
一个 sortedList 列表存储排序结果。
递归处理每个节点：

对每个未访问的节点进行 DFS。
在 DFS 过程中，先处理所有依赖的节点，确保依赖的节点先于当前节点加入结果列表。
结果反转：

因为 DFS 是在递归回溯时才加入结果列表，所以需要将最终结果反转以得到正确的拓扑排序。
代码实现
以下是基于上述思路的具体代码实现：

java
复制代码
import java.util.*;

public class LibrarySorter {
    
    public List<String> sortLibrary(Map<String, Set<String>> libraries) {
        List<String> sortedList = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Set<String> inStack = new HashSet<>();
        
        for (String library : libraries.keySet()) {
            if (!visited.contains(library)) {
                if (!dfs(library, libraries, visited, inStack, sortedList)) {
                    throw new IllegalArgumentException("Cycle detected in dependencies.");
                }
            }
        }
        
        Collections.reverse(sortedList);  // reverse the list to get the correct order
        return sortedList;
    }
    
    private boolean dfs(String current, Map<String, Set<String>> libraries, Set<String> visited, Set<String> inStack, List<String> sortedList) {
        if (inStack.contains(current)) {
            return false;  // cycle detected
        }
        if (visited.contains(current)) {
            return true;  // already processed this node
        }

        visited.add(current);
        inStack.add(current);
        
        for (String dependency : libraries.getOrDefault(current, Collections.emptySet())) {
            if (!dfs(dependency, libraries, visited, inStack, sortedList)) {
                return false;  // cycle detected in recursion
            }
        }
        
        inStack.remove(current);
        sortedList.add(current);
        return true;
    }

    public static void main(String[] args) {
        Map<String, Set<String>> libraries = new HashMap<>();
        libraries.put("A", new HashSet<>(Arrays.asList("B", "C")));
        libraries.put("B", new HashSet<>(Collections.singletonList("D")));
        libraries.put("C", new HashSet<>(Collections.singletonList("D")));
        libraries.put("D", new HashSet<>(Collections.singletonList("E")));
        libraries.put("E", new HashSet<>());

        LibrarySorter sorter = new LibrarySorter();
        List<String> sortedLibraries = sorter.sortLibrary(libraries);

        System.out.println("Sorted Libraries: " + sortedLibraries);
    }
}
总结
构建图：使用 Map<String, Set<String>> 存储节点和依赖关系。
检测环：通过 DFS 过程中的 inStack 检测循环依赖。
拓扑排序：通过 DFS 完成拓扑排序，将结果列表反转以得到正确顺序。
这种方法能够确保在存在依赖关系时正确排序所有库，并处理循环依赖问题。
