public class PowerGridUtils {
    
	// Return the IDs of all elements in the power grid pg in pre-order.
	public static Queue<Integer> collectPreorder(GT<PGElem> pg){
            //back later
               Queue<Integer> q = new LinkedQueue<Integer>();
 if (pg.empty()||pg==null){
                 return q;
             }
            pg.findRoot();
            
            //if(!pg.empty()){
                
                rec_collectPreorder(pg, q);
                        
            
        return q;
        }
        
        public static void rec_collectPreorder(GT<PGElem> pg, Queue<Integer> q){
             
            
                  
                 q.enqueue(pg.retrieve().getId());
                 if(pg.nbChildren()==0){
                     return;
                 }
                    for(int i = 0; i < pg.nbChildren(); i++){
                        pg.findChild(i);
                        rec_collectPreorder(pg, q);
                        pg.findParent();
                    }
              
                
                    
                }
            
            
        
        

        
static boolean flag = false;
	// Searches the power grid pg for the element with ID id. If found, it is made current and true is returned, otherwise false is returned.
	public static boolean find(GT<PGElem> pg, int id){
            //System.out.println("searching for id" + id);
            
            if (pg.empty()||pg==null){
                return false;
            }
                
            else{
                
                
                pg.findRoot();
                if(rec_find(pg, id))
                    return true;

                return false;
                
                
            
        }
        }
        
        private static boolean rec_find(GT<PGElem> pg, int id){
            //System.out.println("searching for id" + id);
            
            
            if (pg.retrieve().getId()==id)
                   return true;
                   //System.out.println("i'M IN 1");
                
                
                  
                
                    //System.out.println("i'M IN 2");
                    for(int i = 0; i < pg.nbChildren(); i++){
                        pg.findChild(i);
                        boolean x =rec_find(pg, id);
                        if(x){
                            return true;
                        }
                            
                        pg.findParent();
                    }
                    return false;
  
        }
        
        
        
	// Add the generator element gen to the power grid pg. This can only be done if the grid is empty. If successful, the method returns true. If there is already a generator, or gen is not of type Generator, false is returned.
	public static boolean addGenerator(GT<PGElem> pg, PGElem gen){
            
            if(pg.empty() && gen.getType() == ElemType.Generator && pg!=null && gen!=null){
                    
                    pg.insert(gen);
                    return true;
                }
            
                    return false;
        }

	// Attaches pgn to the element id and returns true if successful. Note that a consumer can only be attached to a transmitter, and no element can be be attached to it. The tree must not contain more than one generator located at the root. If id does not exist, or there is already aelement with the same ID as pgn, pgn is not attached, and the method retrurns false.
	public static boolean attach(GT<PGElem> pg, PGElem pgn, int id){
            
            
           if(pg==null||pg.empty()||find(pg,pgn.getId())){
                
               return false;
           }
           if(find(pg,id)){
               
               switch(pgn.getType()){
                
                case Generator:
                        return false;
                    
                case Transmitter:
                    

                    if(pg.retrieve().getType().equals(ElemType.Consumer)){
                        
                        return false;
                          } 
                           pg.insert(pgn);
                           
                           return true;
                       
                    
                case Consumer:
                    
                    if(pg.retrieve().getType().equals(ElemType.Transmitter)){
                           
                           pg.insert(pgn);
                           
                           return true;
                       }
                       
                        
                    
                default: return false;
                    
                
            }
           }
            
            
            return false;
            
        }

	// Removes element by ID, all corresponding subtree is removed. If removed, true is returned, false is returned otherwise.
	public static boolean remove(GT<PGElem> pg, int id){
            
            if(pg!=null&&!pg.empty()){
                if(find(pg, id)){
                pg.remove();
                return true;
            }
            }
            
            return false;
        }

	// Computes total power that consumed by a element (and all its subtree). If id is incorrect, -1 is returned.
	public static double totalPower(GT<PGElem> pg, int id){
            
            if(pg== null || pg.empty()){
                return -1;
            }
            if(find(pg, id)){
                
                return rec_totalPower(pg);
            }
            
            
                return -1;
        }
        
        //method here
           private static double rec_totalPower(GT<PGElem> pg){
               double total =0;
            
            
            if(pg.nbChildren()==0){
                switch(pg.retrieve().getType()){
                    case Consumer : return pg.retrieve().getPower();
                    case Generator: return 0;
                    case Transmitter: return 0;
                    default: return 0;
                }
            }
               
                
                    for(int i = 0; i < pg.nbChildren(); i++){
                        pg.findChild(i);
                        total+= rec_totalPower(pg);
                        pg.findParent();
                    }
                    return total;
            
            
           }
                
                    
               
           
            
            //end
                
        //end of method
        
	/* Checks if the power grid contains an overload. The method returns the ID 
        of the first element preorder that has an overload and -1 if there is no overload. */
	public static int findOverload(GT<PGElem> pg){
            
            
            if(pg!=null&&!pg.empty()){
               pg.findRoot();
               return rec_findOverload(pg, pg.retrieve().getPower());
            }
                        return -1;
                    }
                     
                
        
        
        private static int rec_findOverload(GT<PGElem> pg, double ov){
            boolean possible = pg.retrieve().getType()==ElemType.Generator||pg.retrieve().getType()==ElemType.Transmitter;
            
            if(possible && pg.nbChildren()!=0){
                
                int id = pg.retrieve().getId();
                double tp = totalPower(pg, id);
               if (tp>ov){
                   return pg.retrieve().getId();
               }else{
                   find(pg, id);
                   for(int i = 0; i < pg.nbChildren(); i++){
                        pg.findChild(i);
                        ov = pg.retrieve().getPower();
                        int recOv = rec_findOverload(pg, ov);
                        if(recOv!=-1)
                            return recOv;
                        else
                        pg.findParent();
               }
               }
            }
                    return -1;
                    
        }
}
            


        
            
            
           
        

