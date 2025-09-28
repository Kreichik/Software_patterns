using UnityEngine;
using UnityEngine.UI;

public class UIManager : MonoBehaviour
{
    [Header("UI References")]
    public GameObject healthBarPrefab;
    public Transform blueTeamUIParent;
    public Transform greenTeamUIParent;

    void Start()
    {
        Invoke("SetupHealthBars", 0.1f);
    }

    void SetupHealthBars()
    {
        CharacterStats[] allPawns = FindObjectsOfType<CharacterStats>();

        foreach (CharacterStats pawn in allPawns)
        {
            Transform parentTransform = null;
            if (pawn.CompareTag("BluePawn"))
            {
                parentTransform = blueTeamUIParent;
            }
            else if (pawn.CompareTag("GreenPawn"))
            {
                parentTransform = greenTeamUIParent;
            }

            if (parentTransform != null)
            {
                GameObject healthBarGO = Instantiate(healthBarPrefab, parentTransform);
                Slider healthBarSlider = healthBarGO.GetComponent<Slider>();

                pawn.OnHealthChanged.AddListener((healthPercentage) => {
                    healthBarSlider.value = healthPercentage;
                });
            }
        }
    }
}