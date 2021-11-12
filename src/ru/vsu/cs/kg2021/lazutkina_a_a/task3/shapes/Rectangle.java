package ru.vsu.cs.kg2021.lazutkina_a_a.task3.shapes;

import ru.vsu.cs.kg2021.lazutkina_a_a.task3.RealPoint;

public class Rectangle
{
    RealPoint leftUpperPoint;
    RealPoint rightLowerPoint;

    public Rectangle(RealPoint leftUpperPoint, RealPoint rightLowerPoint)
    {
        this.leftUpperPoint = leftUpperPoint;
        this.rightLowerPoint = rightLowerPoint;
    }

    public RealPoint getLeftUpperPoint()
    {
        return leftUpperPoint;
    }

    public void setLeftUpperPoint(RealPoint leftUpperPoint)
    {
        this.leftUpperPoint = leftUpperPoint;
    }

    public RealPoint getRightLowerPoint()
    {
        return rightLowerPoint;
    }

    public void setRightLowerPoint(RealPoint rightLowerPoint)
    {
        this.rightLowerPoint = rightLowerPoint;
    }
}
