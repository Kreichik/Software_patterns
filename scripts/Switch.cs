using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FourCharacterSwitcher : MonoBehaviour
{
    
    public GameObject char1, char2, char3, char4;

    int charOn = 1;

    void Start()
    {
        char1.gameObject.SetActive(true);
        char2.gameObject.SetActive(false);
        char3.gameObject.SetActive(false);
        char4.gameObject.SetActive(false);
    }

    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Tab))
        {
            SwitchCharacter();
        }
    }

    public void SwitchCharacter()
    {
        switch (charOn)
        {
            case 1:
                charOn = 2;
                char1.gameObject.SetActive(false);
                char2.gameObject.SetActive(true);
                char3.gameObject.SetActive(false);
                char4.gameObject.SetActive(false);
                break;

            case 2:
                charOn = 3;
                char1.gameObject.SetActive(false);
                char2.gameObject.SetActive(false);
                char3.gameObject.SetActive(true);
                char4.gameObject.SetActive(false);
                break;

            case 3:
                charOn = 4;
                char1.gameObject.SetActive(false);
                char2.gameObject.SetActive(false);
                char3.gameObject.SetActive(false);
                char4.gameObject.SetActive(true);
                break;

            case 4:
                charOn = 1;
                char1.gameObject.SetActive(true);
                char2.gameObject.SetActive(false);
                char3.gameObject.SetActive(false);
                char4.gameObject.SetActive(false);
                break;
        }
    }
}