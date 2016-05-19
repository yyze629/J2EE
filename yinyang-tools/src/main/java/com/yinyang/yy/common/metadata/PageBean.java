package com.yinyang.yy.common.metadata;

import java.io.Serializable;

public class PageBean
  implements Serializable
{
  private static final long serialVersionUID = -1367487012L;
  private static int DEF_PAGE_VIEW_SIZE = 20;
  private int page;
  private int pageSize;
  private int count;

  public PageBean(int page, int pageSize)
  {
    this.page = ((page <= 0) ? 1 : page);
    this.pageSize = ((pageSize <= 0) ? DEF_PAGE_VIEW_SIZE : pageSize);
  }

  public int getPage()
  {
    return ((this.page <= 0) ? 1 : this.page);
  }

  public void setPage(int page)
  {
    this.page = page;
  }

  public int getPageSize()
  {
    return ((this.pageSize <= 0) ? DEF_PAGE_VIEW_SIZE : this.pageSize);
  }

  public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize;
  }

  public long getCount()
  {
    return this.count;
  }

  public void setCount(int count)
  {
    this.count = ((count < 0) ? 0 : count);
    if (this.page > getPages())
      this.page = getPages();
  }

  public int getPages()
  {
    return ((this.count + getPageSize() - 1) / getPageSize());
  }

  public int getStartNo()
  {
    return ((getPage() - 1) * getPageSize() + 1);
  }

  public int getEndNo()
  {
    return Math.min(getPage() * getPageSize(), this.count);
  }

  public int getPrePageNo()
  {
    return Math.max(getPage() - 1, 1);
  }

  public int getNextPageNo()
  {
    return Math.min(getPage() + 1, getPages());
  }
}