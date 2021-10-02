import java.util.Arrays;

class KMST{

    class Edge implements Comparable<Edge>{
        int src,dest,weight;
        public int compareTo(Edge compareEdge){
            return this.weight-compareEdge.weight;
        }
    };

    class subset{
        int p,r;
    };
    int V,E;
    Edge edge[];

    KMST(int v,int e){
        V=v;
        E=e;
        edge=new Edge[E];
        for(int i=0;i<e;++i){
            edge[i]=new Edge();
        }
    }

    int find(subset subsets[],int i){
        if(subsets[i].p!=i){
            subsets[i].p=find(subsets, subsets[i].p);
        }
        return subsets[i].p;
    }
    void Union(subset subsets[],int x,int y){
        int xroot=find(subsets,x);
        int yroot=find(subsets,y);

        if(subsets[xroot].r<subsets[yroot].r)
            subsets[xroot].p=yroot;
        else if(subsets[xroot].r>subsets[yroot].r)
            subsets[yroot].p=xroot;
        else{
            subsets[yroot].p=xroot;
            subsets[xroot].r++;
        }

    }

    void Kruskal(){
        Edge result[]=new Edge[V];
        int e=0;
        int i=0;
        for(i=0;i<V;++i){
            result[i]=new Edge();
        }
        Arrays.sort(edge);
        subset subsets[]=new subset[V];
        for(i=0;i<V;++i)
            subsets[i]=new subset();
        for(int j=0;j<V;++j){
            subsets[j].p=j;
            subsets[j].r=0;
        }
        i=0;
        while(e<V-1){
            Edge next_edge=edge[i++];
            int x=find(subsets,next_edge.src);
            int y=find(subsets,next_edge.dest);
            if(x!=y){
                result[e++]=next_edge;
                Union(subsets,x,y);
            }
        }

        System.out.println("Edges: \tWeights:");
        int minCost=0;
        for(i=0;i<e;++i){
            System.out.println(result[i].src+" - "+result[i].dest+" \t "+result[i].weight);
            minCost+=result[i].weight;
        }
        System.out.println("Minimum Cost of this is: "+ minCost);
    }

}

public class kruskals {

    public static void main(String[] args) {
        int V=4, E=5;
        KMST ob=new KMST(V,E);

        ob.edge[0].src=0;
        ob.edge[0].dest=1;
        ob.edge[0].weight=10;

        ob.edge[1].src=0;
        ob.edge[1].dest=2;
        ob.edge[1].weight=6;

        ob.edge[2].src=0;
        ob.edge[2].dest=3;
        ob.edge[2].weight=5;

        ob.edge[3].src=1;
        ob.edge[3].dest=3;
        ob.edge[3].weight=15;

        ob.edge[4].src=2;
        ob.edge[4].dest=3;
        ob.edge[4].weight=4;

        ob.Kruskal();
    }

}