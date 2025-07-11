package io.schupke.editor.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

public class VerticalLayout implements LayoutManager{

  public final static int CENTER=0;
  public final static int RIGHT=1;
  public final static int LEFT=2;
  public final static int BOTH=3;

  public final static int TOP=1;
  public final static int BOTTOM=2;
  private int vgap; //the vertical vgap between components...defaults to 5
  private int alignment; //LEFT, RIGHT, CENTER or BOTH...how the components are justified
  private int anchor; //TOP, BOTTOM or CENTER ...where are the components positioned in an overlarge space
  //@SuppressWarnings("rawtypes")
//private Hashtable comps;

//Constructors
   public VerticalLayout(){
  this(5,CENTER,TOP);
   }
   public VerticalLayout(int vgap){
  this(vgap,CENTER,TOP);
   }
   public VerticalLayout(int vgap,int alignment){
  this(vgap,alignment,TOP);
   }
   public VerticalLayout(int vgap,int alignment,int anchor){
  this.vgap=vgap; this.alignment=alignment; this.anchor=anchor;
   }
//----------------------------------------------------------------------------
   private Dimension layoutSize(Container parent,boolean minimum){
  Dimension dim=new Dimension(0,0);
  Dimension d;
  synchronized(parent.getTreeLock()){
    int n=parent.getComponentCount();
    for(int i=0;i<n;i++){
      Component c=parent.getComponent(i);
      if(c.isVisible()){
        d=minimum ? c.getMinimumSize() : c.getPreferredSize();
        dim.width=Math.max(dim.width,d.width); dim.height+=d.height;
        if(i>0)dim.height+=vgap;
      }
    }
  }
  Insets insets=parent.getInsets();
  dim.width+=insets.left+insets.right;
  dim.height+=insets.top+insets.bottom+vgap+vgap;
  return dim;
   }
//-----------------------------------------------------------------------------
   public void layoutContainer(Container parent){
  Insets insets=parent.getInsets();
  synchronized(parent.getTreeLock()){
    int n=parent.getComponentCount();
    Dimension pd=parent.getSize(); int y=0;
//work out the total size
    for(int i=0;i<n;i++){
      Component c=parent.getComponent(i);
      Dimension d=c.getPreferredSize();
      y+=d.height+vgap;
    }
    y-=vgap; //otherwise there's a vgap too many
//Work out the anchor paint
    if(anchor==TOP)y=insets.top;
    else if(anchor==CENTER)y=(pd.height-y)/2;
    else y=pd.height-y-insets.bottom;
//do layout
    for(int i=0;i<n;i++){
      Component c=parent.getComponent(i);
      Dimension d=c.getPreferredSize();
      int x=insets.left; int wid=d.width;
      if(alignment==CENTER)x=(pd.width-d.width)/2;
      else if(alignment==RIGHT)x=pd.width-d.width-insets.right;
      else if(alignment==BOTH)wid=pd.width-insets.left-insets.right;
      c.setBounds(x,y,wid,d.height);
      y+=d.height+vgap;
    }
  }
   }
//-----------------------------------------------------------------------------
   public Dimension minimumLayoutSize(Container parent){return layoutSize(parent,false);}
//-----------------------------------------------------------------------------
   public Dimension preferredLayoutSize(Container parent){return layoutSize(parent,false);}
//----------------------------------------------------------------------------
   public void addLayoutComponent(String name,Component comp){}
//-----------------------------------------------------------------------------
   public void removeLayoutComponent(Component comp){}
//-----------------------------------------------------------------------------
   public String toString(){return getClass().getName()+"[vgap="+vgap+" align="+alignment+" anchor="+anchor+"]";}
}
