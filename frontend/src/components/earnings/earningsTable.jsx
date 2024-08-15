import DataTableColumn from '@salesforce/design-system-react/components/data-table/column';

import BaseTable from 'components/base/baseTable';
import BaseTableNumberCell from 'components/base/baseTableNumberCell';
import BaseTableSemesterCell from 'components/base/baseTableSemesterCell';

const COLUMNS = [
  <DataTableColumn key="art" label="Beschreibung" property="art" />,
  <DataTableColumn key="betrag" label="Betrag" property="betrag">
    <BaseTableNumberCell />
  </DataTableColumn>,
  <DataTableColumn key="semester" label="Semester" property="semester">
    <BaseTableSemesterCell />
  </DataTableColumn>
];

const ROW_ACTIONS = [
  {
    id: 'edit',
    label: 'Bearbeiten',
    value: 'edit'
  },
  {
    id: 'delete',
    label: 'Löschen',
    value: 'delete'
  }
];

export default function EarningsTable({ earnings, onUpdate, onDelete }) {
  const handleRowAction = (item, action) => {
    switch (action.value) {
      case 'edit':
        onUpdate(item);
        break;
      case 'delete':
        onDelete(item.art);
        break;
      default:
        console.error('Not defined');
        break;
    }
  };

  if (!earnings.length) {
    return null;
  }

  return (
    <BaseTable
      items={earnings}
      columns={COLUMNS}
      rowActions={ROW_ACTIONS}
      onRowAction={handleRowAction}
      maxHeight="70vh"
    />
  );
}
