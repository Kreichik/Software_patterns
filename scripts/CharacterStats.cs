using System.Diagnostics;
using UnityEngine;
using UnityEngine.Events;

public class CharacterStats : MonoBehaviour
{
    public int maxHealth = 100;
    public int currentHealth;

    public UnityEvent<float> OnHealthChanged;

    void Awake()
    {
        currentHealth = maxHealth;
    }

    public void TakeDamage(int damage)
    {
        currentHealth -= damage;
        if (currentHealth < 0)
        {
            currentHealth = 0;
        }

        float healthPercentage = (float)currentHealth / maxHealth;

        OnHealthChanged.Invoke(healthPercentage);

        if (currentHealth <= 0)
        {
            Debug.Log(gameObject.name + " has died!");
        }
    }
}