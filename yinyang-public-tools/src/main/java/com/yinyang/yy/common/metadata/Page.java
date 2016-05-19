package com.yinyang.yy.common.metadata;

import java.io.Serializable;
import java.util.List;

public class Page
  implements Serializable
{
  private static final long serialVersionUID = 142498596L;
  private List result;
  private PageBean pageBean;

  public Page(List lstResult, PageBean pageBean)
  {
    this.result = lstResult;
    this.pageBean = pageBean;
  }

  public List getResult()
  {
    return this.result;
  }

  public void setResult(List lstResult)
  {
    this.result = lstResult;
  }

  public PageBean getPageBean()
  {
    return this.pageBean;
  }

  public void setPageBean(PageBean pageBean)
  {
    this.pageBean = pageBean;
  }
}