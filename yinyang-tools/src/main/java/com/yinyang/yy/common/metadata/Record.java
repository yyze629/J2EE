package com.yinyang.yy.common.metadata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Record
  implements Serializable
{
  private static final long serialVersionUID = -2015343244L;
  private Map map = new HashMap();

  public Map getMap()
  {
    return this.map;
  }

  public void setMap(Map map) {
    this.map = map;
  }
}