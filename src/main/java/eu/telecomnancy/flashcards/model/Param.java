package eu.telecomnancy.flashcards.model;

public class Param
{
    private Boolean oneTime;
    private int second;
    private Boolean isSecond;

    public Param()
    {
        this.oneTime = false;
        this.second = 10;
        this.isSecond = false;
    }

    public Param(Boolean oneTime, int second, Boolean isSecond)
    {
        this.oneTime = oneTime;
        this.second = second;
        this.isSecond = isSecond;
    }

    public void setSecond(int second)
    {
        this.second = second;
    }

    public int getsecond()
    {
        return this.second;
    }

    public void setOneTime(Boolean oneTime)
    {
        this.oneTime = oneTime;
    }

    public Boolean getOneTime()
    {
        return this.oneTime;
    }

    public void setIsSecond(Boolean isSecond)
    {
        this.isSecond = isSecond;
    }

    public Boolean getisSecond()
    {
        return this.isSecond;
    }
}  