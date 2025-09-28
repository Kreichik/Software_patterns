using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class SelectionManager : MonoBehaviour
{
    public GameObject moveIndicatorPrefab;
    private List<GameObject> moveIndicators = new List<GameObject>();

    private GameObject selectedPawn;
    private Camera mainCamera;

    private bool isMoving = false;

    void Start()
    {
        mainCamera = Camera.main;
        InitialSetupPawns();
    }

    void InitialSetupPawns()
    {
        Tile[] allTiles = FindObjectsOfType<Tile>();
        foreach (var tile in allTiles)
        {
            RaycastHit hit;
            if (Physics.Raycast(tile.transform.position, Vector3.down, out hit, 5f))
            {
                if (hit.collider.CompareTag("GreenPawn") || hit.collider.CompareTag("BluePawn"))
                {
                    tile.occupantPawn = hit.collider.gameObject;
                }
            }
        }
    }

    void Update()
    {
        if (isMoving) return;

        if (Input.GetMouseButtonDown(0))
        {
            Ray ray = mainCamera.ScreenPointToRay(Input.mousePosition);
            RaycastHit hit;

            if (Physics.Raycast(ray, out hit))
            {
                GameObject clickedObject = hit.collider.gameObject;

                if (clickedObject.CompareTag("GreenPawn") || clickedObject.CompareTag("BluePawn"))
                {
                    SelectPawn(clickedObject);
                }
                else if (clickedObject.CompareTag("MoveIndicator"))
                {
                    Tile targetTile = clickedObject.GetComponentInParent<Tile>();
                    if (selectedPawn != null && targetTile != null)
                    {
                        MovePawn(targetTile);
                    }
                }
                else
                {
                    DeselectPawn();
                }
            }
            else
            {
                DeselectPawn();
            }
        }
    }

    private void SelectPawn(GameObject pawn)
    {
        if (selectedPawn == pawn) return;

        DeselectPawn();

        selectedPawn = pawn;
        SetHighlight(selectedPawn, true);
        ShowPossibleMoves(selectedPawn);
    }

    private void DeselectPawn()
    {
        if (selectedPawn != null)
        {
            SetHighlight(selectedPawn, false);
            selectedPawn = null;
        }
        ClearPossibleMoves();
    }

    private void SetHighlight(GameObject pawn, bool isHighlighted)
    {
        Renderer pawnRenderer = pawn.GetComponent<Renderer>();
        if (isHighlighted)
        {
            pawnRenderer.material.EnableKeyword("_EMISSION");
        }
        else
        {
            pawnRenderer.material.DisableKeyword("_EMISSION");
        }
    }

    private void ShowPossibleMoves(GameObject pawn)
    {
        ClearPossibleMoves();

        Debug.Log("--- Начинаю поиск ходов для: " + pawn.name + " ---");

        Tile currentTile = FindTileOfPawn(pawn);
        if (currentTile == null)
        {
            Debug.LogError("КРИТИЧЕСКАЯ ОШИБКА: Пешка " + pawn.name + " не найдена ни на одной клетке! Проверьте коллайдеры и теги.");
            return;
        }

        Debug.Log("Пешка стоит на клетке (" + currentTile.x + ", " + currentTile.z + ")");

        int forwardDirectionX = 0;
        if (pawn.CompareTag("BluePawn")) { forwardDirectionX = 1; }
        else if (pawn.CompareTag("GreenPawn")) { forwardDirectionX = -1; }

        int targetX = currentTile.x + forwardDirectionX;
        int targetZ = currentTile.z;

        Debug.Log("Проверяю клетку впереди по координатам (" + targetX + ", " + targetZ + ")");

        Tile targetTile = FindTileAtCoordinates(targetX, targetZ);
        if (targetTile == null)
        {
            Debug.LogWarning("Ход невозможен: клетка (" + targetX + ", " + targetZ + ") находится за пределами поля.");
            return;
        }

        if (targetTile.occupantPawn != null)
        {
            Debug.LogWarning("Ход невозможен: клетка (" + targetX + ", " + targetZ + ") занята объектом " + targetTile.occupantPawn.name);
            return;
        }

        Debug.Log("Все проверки пройдены! Создаю индикатор на клетке (" + targetX + ", " + targetZ + ")");
        GameObject indicator = Instantiate(moveIndicatorPrefab, targetTile.transform.position, Quaternion.Euler(90, 0, 0), targetTile.transform);
        indicator.tag = "MoveIndicator";
        moveIndicators.Add(indicator);
    }

    private void ClearPossibleMoves()
    {
        foreach (var indicator in moveIndicators)
        {
            Destroy(indicator);
        }
        moveIndicators.Clear();
    }

    private void MovePawn(Tile targetTile)
    {
        isMoving = true;
        ClearPossibleMoves();

        Tile startTile = FindTileOfPawn(selectedPawn);

        if (startTile != null) startTile.occupantPawn = null;
        targetTile.occupantPawn = selectedPawn;

        StartCoroutine(MovePawnCoroutine(selectedPawn, targetTile.transform.position));

        SetHighlight(selectedPawn, false);
        selectedPawn = null;
    }

    IEnumerator MovePawnCoroutine(GameObject pawn, Vector3 targetPosition)
    {
        float duration = 0.3f;
        float elapsedTime = 0;
        Vector3 startingPosition = pawn.transform.position;

        while (elapsedTime < duration)
        {
            pawn.transform.position = Vector3.Lerp(startingPosition, targetPosition, (elapsedTime / duration));
            elapsedTime += Time.deltaTime;
            yield return null;
        }

        pawn.transform.position = targetPosition;
        isMoving = false;
    }

    private Tile FindTileOfPawn(GameObject pawn)
    {
        Tile[] allTiles = FindObjectsOfType<Tile>();
        foreach (var tile in allTiles)
        {
            if (tile.occupantPawn == pawn)
            {
                return tile;
            }
        }
        return null;
    }

    private Tile FindTileAtCoordinates(int x, int z)
    {
        if (x < 0 || x > 3 || z < 0 || z > 3)
        {
            return null;
        }

        Tile[] allTiles = FindObjectsOfType<Tile>();
        foreach (var tile in allTiles)
        {
            if (tile.x == x && tile.z == z)
            {
                return tile;
            }
        }
        return null;
    }
}